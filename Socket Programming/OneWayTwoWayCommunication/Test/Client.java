import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    
    public static void main(String[] arg) throws IOException{

        Socket sock=new Socket("localhost",5000);
        System.out.println("Client is connected port: "+sock.getLocalPort());
        System.out.println("Client connected...");


        Scanner input=new Scanner(System.in);
        DataInputStream in=new DataInputStream(sock.getInputStream());
        DataOutputStream out=new DataOutputStream(sock.getOutputStream());
        while(true){
            System.out.println("Enter your Number: ");

            String num=input.nextLine();
            out.writeUTF(num);

            if(num.equalsIgnoreCase("Exit")||num.equalsIgnoreCase("End")){
                System.out.println("Connection Closed....");
                break;
            }

            String res=in.readUTF();
            System.out.println("Server Respose: "+res);
        }
    }

    private static void colsedConnection(Socket s,DataInputStream in,DataOutputStream out){
        in.close();
        out.close();
        s.close();

    }

}
