package Practice;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;
import Practice.MultithreadedServer;


public class MultithreadedServer {

    private static final int MAX_CLIENTS = 5;
    static final AtomicInteger clientCount = new AtomicInteger(0);

    public static void main(String[] args) throws IOException {

        try (ServerSocket sock = new ServerSocket(5000)) {
            System.out.println("Server connected at " + sock.getLocalPort());
            System.out.println("Server is connected\n");
            System.out.println("Waiting for the client\n");

            while (clientCount.get() < MAX_CLIENTS) {
                Socket clientSock = sock.accept();
                System.out.println("A new client is connected " + clientSock);
                DataOutputStream dos=new DataOutputStream(clientSock.getOutputStream());
            DataInputStream dis=new DataInputStream(clientSock.getInputStream());
            System.out.println("A new thread is assigning");
           

                if (clientCount.incrementAndGet() == MAX_CLIENTS) {
                    System.out.println("Maximum clients reached, closing server");
                    break;
                }

                //  Thread newThread=new ClientHandler(clientSock,dis,dos);
                Thread newThread=new ClientHandler(clientSock,dis,dos);
                newThread.start();
            }
        }
    }
}
