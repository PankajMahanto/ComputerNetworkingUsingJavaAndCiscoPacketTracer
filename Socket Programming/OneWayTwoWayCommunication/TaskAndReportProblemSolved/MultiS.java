// In our previous task, server can not be closed automatically. Server can create infinite connections in its lifetime.
// However, in this task, server can not create connections with infinite number of client. The server can serve
// at most 5 clients in its lifetime. If the 6th client comes to get connected or just completing giving service to
// exactly 5 clients, the server closes down automatically.
// For that reason, you need to convert the infinite loop to a conditional loop. You can do it using while/for
// loop and you have to keep a counter for counting the number of client those have been connected till now.
// When a new client has come, the value of counter has been incremented by one and when the counter value is
// equal to 10, then the server close its connection.
// Then, you need to do your necessary task i.e., converting a string to uppercase string in the run() method
// under the ClientHandler class by changing simple Date-Time server code. You can easily convert a string to
// Uppercase string by using java build-in function toUpperCase() method.



package TaskAndReportProblemSolved;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiS {
    private static final int MAX_CLIENTS = 5;
    private static int clientCount = 0;

    public static void main(String[] args) throws IOException {

        try (ServerSocket sock = new ServerSocket(5000)) {
            System.out.println("Server connected at " + sock.getLocalPort());
            System.out.println("Server is connected\n");
            System.out.println("Waiting for the client\n");

            while (clientCount < MAX_CLIENTS) {
                Socket clientSock = sock.accept();
                System.out.println("A new client is connected " + clientSock);

                clientCount++;

                if (clientCount == MAX_CLIENTS) {
                    System.out.println("Maximum clients reached, closing server");
                    break;
                }

                Thread newThread = new CHandler(clientSock);
                newThread.start();
            }
        }
    }
}
