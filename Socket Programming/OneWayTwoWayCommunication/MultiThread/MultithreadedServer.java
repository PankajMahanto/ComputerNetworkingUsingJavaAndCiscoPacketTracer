// package MultiThread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultithreadedServer {

    public static void main(String[] args) throws IOException {
        ServerSocket sock = new ServerSocket(5000);
        System.out.println("Server connected at "+sock.getLocalPort());
        System.out.println("Server is connected\n");
        System.out.println("Waiting for the client\n");

        while (true) {
            Socket clientSock = sock.accept();
            sock.close();
            System.out.println("A new client is connected "+clientSock);
            DataOutputStream dos=new DataOutputStream(clientSock.getOutputStream());
            DataInputStream dis=new DataInputStream(clientSock.getInputStream());
            System.out.println("A new thread is assigning");
            Thread newThread=new ClientHandler(clientSock,dis,dos);
            newThread.start();
        }
       
    
    }
}

// class ClientHandlerThread extends Thread {

//     private Socket clientSocket;

//     public ClientHandlerThread(Socket clientSocket) {
//         this.clientSocket = clientSocket;
//     }

//     @Override
//     public void run() {
//         try {
//             DataInputStream input = new DataInputStream(clientSocket.getInputStream());
//             DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());

//             String message;
//             while ((message = input.readUTF()) != null) {
//                 System.out.println("Client says: " + message);
//                 String response = "Server response: " + message.toUpperCase();
//                 output.writeUTF(response);
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         } finally {
//             try {
//                 clientSocket.close();
//             } catch (IOException e) {
//                 e.printStackTrace();
//             }
//         }
//     }
// }

//MultithreadedServer

//MultithreadedClient