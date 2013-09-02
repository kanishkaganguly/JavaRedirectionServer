JavaRedirectionServer
=====================

A simple redirection server created in Java. Takes requests from multiple clients and redirects them to the respective ServiceServer.

The RedirectionServer takes requests from the RedirectionClient in the form of "Service 1" or "Service 2" (more can be added as required
and then transfers them to the required ServiceServer.
Once the ServiceServer returns the required service (text based, in this case), the RedirectionServer sends it back to the Client that requested
for it.

The main application of such a system is in mirror servers, where the actual file may be stored in one of several servers but the client only knows
the location of one host server. It is up to the server to redirect the request and serve the client correctly.
