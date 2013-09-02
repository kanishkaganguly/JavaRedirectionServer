package redirectionserver;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.UnknownHostException;

public class RedirectionServer {

    public static void main(String args[]) {

        ServerSocket echoServer = null;
        String line;
        DataInputStream is;
        PrintStream os;
        Socket clientSocket = null;

        try {
            echoServer = new ServerSocket(2222);
        } catch (IOException e) {
            System.out.println(e);
        }


        System.out.println("The server started.");
        try {
            clientSocket = echoServer.accept();
            System.out.println("Client :" + clientSocket.getInetAddress() + ":" + clientSocket.getPort() + " CONNECTED");
            is = new DataInputStream(clientSocket.getInputStream());
            os = new PrintStream(clientSocket.getOutputStream());


            while (true) {
                line = is.readLine();
                System.out.println("Received From " + clientSocket.getInetAddress() + ":" + clientSocket.getPort() + " <" + line + ">");


                System.out.println("Sending Request to Remote Server");
                Socket clientSocketX = null;
                DataInputStream isX = null;
                PrintStream osX = null;
                DataInputStream inputLine = null;

                try {
                    if (line.equalsIgnoreCase("Service 1")) {
                        clientSocketX = new Socket("localhost", 6789);
                    } else if (line.equalsIgnoreCase("Service 2")) {
                        clientSocketX = new Socket("localhost", 9876);
                    } else if (line.equalsIgnoreCase("Ok")) {
                        System.exit(0);
                    }
                    System.out.println("Connected to Remote Server");
                    osX = new PrintStream(clientSocketX.getOutputStream());
                    isX = new DataInputStream(clientSocketX.getInputStream());
                    inputLine = new DataInputStream(new BufferedInputStream(System.in));
                } catch (UnknownHostException e) {
                    System.err.println("Don't know about host");
                } catch (IOException e) {
                    System.err.println("Couldn't get I/O for the connection to host");
                }


                if (clientSocketX != null && osX != null && isX != null) {
                    try {

                        String responseLine;

                        while ((responseLine = isX.readLine()) != null) {
                            System.out.println(responseLine);
                            if (responseLine.indexOf("Ok") != -1) {
                                break;
                            }

                            os.println(responseLine);
                        }

                        osX.close();
                        isX.close();
                        clientSocketX.close();
                    } catch (UnknownHostException e) {
                        System.err.println("Trying to connect to unknown host: " + e);
                    } catch (IOException e) {
                        System.err.println("IOException:  " + e);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
