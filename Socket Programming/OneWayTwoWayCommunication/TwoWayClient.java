import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TwoWayClient {
    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket sock=new Socket("localhost",5000);

        System.out.println("Client is connected in port at: "+sock.getLocalPort());
        Scanner input=new Scanner(System.in);
        DataOutputStream write=new DataOutputStream(sock.getOutputStream());
        DataInputStream read=new DataInputStream(sock.getInputStream());

        String str="";

        while(!str.equalsIgnoreCase("over")){
            System.out.print("Client Says: ");
            str=input.nextLine();
            write.writeUTF(str);

            String respose=read.readUTF();
            System.out.println("Server Respose: "+respose);
        }
        input.close();
        input.close();
        sock.close();
    }
}
