package TaskAndReportProblemSolved;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class C {
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
