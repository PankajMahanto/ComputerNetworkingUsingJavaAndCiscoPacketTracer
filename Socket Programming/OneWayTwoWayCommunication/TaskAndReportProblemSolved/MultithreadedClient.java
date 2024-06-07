
// Create a simple basic thread application for multiple clients to be connected with a single server. Here each
// client sends a string to the single server. The server returns the string in all uppercase to that client. After
// receiving the uppercase string from the server, that client closes down automatically. The server can serve at
// most 5 clients in its lifetime. If the 6th client comes to get connected or just completing giving service to exactly
// 5 clients, the server closes down automatically.


package TaskAndReportProblemSolved;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MultithreadedClient {

    public static void main(String[] args) throws IOException {

        try {
            Socket clientSock = new Socket("localhost", 5000);
            System.out.println("Connected at server Handshaking port " + clientSock.getPort());
            System.out.println("Client is Connected");

            Scanner scanner = new Scanner(System.in);
            DataOutputStream dos = new DataOutputStream(clientSock.getOutputStream());
            DataInputStream dis = new DataInputStream(clientSock.getInputStream());

            while (true) {
                String inStr = dis.readUTF();
                System.out.println(inStr);

                String outStr = scanner.nextLine();
                dos.writeUTF(outStr);

                if (outStr.equals("Exit")) {
                    System.out.println("Closing the Connecting " + clientSock);
                    clientSock.close();
                    System.out.println("Connection Closed");
                    break;
                }

                String received = dis.readUTF();
                System.out.println("Received: " + received);
            }

            dos.close();
            dis.close();
            clientSock.close();
            scanner.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
