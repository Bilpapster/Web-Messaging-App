# Client-Server Messaging Application

In this repository you can find `Java` source code for a Client-Server
Messaging Application. It currently is a command line interfaced application,
with an intention to add a graphical user interface later on. 
The application uses the `Java RMI` technology. It is originally developed
alongside with the course of *Communication Networks* that the author attended
during their 5 <sup>th</sup> semester of studies at the 
[School of Informatics, AUTh](https://www.csd.auth.gr/en/).

The application consists of both a Server and a Client end, supporting multiple
client requests in parallel, utilizing to the `Java RMI` mechanism. More specifically,
the Server (`Server.java`) runs continuously, waiting for client requests. The 
client(s) (`MessagingClient.java`) send request to the server, get the server
response and then terminate. 

The `RequestProcessor.java`class serves as an 
intermediate request handler between the client and the server side. The latter
is the class that is passed into the RMI mechanism, making it feasible to get
its methods remotely invoked by the client side, whilst communicating with 
the server one at the same time. In order to accomplish this, a specially structured
interface (`CommunicationProtocol.java`) is defined and implemented by the 
`RequestProcessor` class.

The `Account.java` and `Message.java` are simple classes that intuitively model
the entities of the application with no extra complexity. Lastly, the
`RandomEngine.java` is a class that is designed and implemented accordingly with
the *Singleton* design pattern and offers a concise way of accessing a random 
numbers generator throughout the classes of the application.

The messaging application supports six different operations, so far.  
In particular, a client can:
- ***create an account***, using a unique username (required for the rest operations)
- ***print the usernames*** of all registered users in the application
- ***send/receive messages*** to/from other users
- ***print*** a thumbnail of their ***inbox*** messages
- ***read messages***
- ***delete messages*** from their inbox
