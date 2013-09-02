package redirectionserver;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;

public class ServicesServer2 {

    public static void main(String args[]) {

        ServerSocket echoServer = null;
        String line;
        DataInputStream is;
        PrintStream os;
        Socket clientSocket = null;

        try {
            echoServer = new ServerSocket(9876);
        } catch (IOException e) {
            System.out.println(e);
        }

        System.out.println("The Service 2 Server started.");
        try {
            clientSocket = echoServer.accept();
            System.out.println(clientSocket.getInetAddress() + ":" + clientSocket.getPort() + " CONNECTED");
            is = new DataInputStream(clientSocket.getInputStream());
            os = new PrintStream(clientSocket.getOutputStream());
            System.out.println("Service 2 Sent");
            os.println("From service_server 2: Service 2 Sent");
            os.println("Ok");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}