package Test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int port=5000;
    private static final int max=5;
    

    public static void main(String[] arg) throws IOException{

        ServerSocket sock=new ServerSocket(port);
        System.out.println("Server is connected at port Number: "+sock.getLocalPort());
        System.out.println("Wait for the Client!....");

        while(true){
            Socket cSock=sock.accept();
            System.out.println("A new Client is Connected!.....");
            DataInputStream in=new DataInputStream(cSock.getInputStream());
            DataOutputStream out=new DataOutputStream(cSock.getOutputStream());

            new ClientHandler(cSock).start();;
            
            sock.close();
        }
        // sock.close();
    }
}

