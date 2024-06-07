
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TwoWayServer {
    public static void main(String[] args) throws IOException {
        ServerSocket sock=new ServerSocket(5000);

        System.out.println("Server is connected at port: "+sock.getLocalPort());
        System.out.println("Waiting for the client....");

        Socket sockC=sock.accept();
        DataInputStream read=new DataInputStream(sockC.getInputStream());
       DataOutputStream write=new DataOutputStream(sockC.getOutputStream());
        Scanner input=new Scanner(System.in);

        String str="";

        while(!str.equalsIgnoreCase("over")){
            str=read.readUTF();
            System.out.println("Client Says: "+str);
            System.out.print("Server Says: ");
            String str2=input.nextLine();
            write.writeUTF(str2);
        }
        read.close();
        sock.close();
        input.close();
    }
}
