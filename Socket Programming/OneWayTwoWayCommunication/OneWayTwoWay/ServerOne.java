//package OneWayTwoWay;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerOne {
    

    public static void main(String[] args) throws IOException{
        ServerSocket sock=new ServerSocket(5000);
        System.out.println("Server is connected at port no: "+sock.getLocalPort());
        System.out.println("Server is connected\n");
        System.out.println("Waiting for the Client\n");

        Socket s=sock.accept();
        System.out.println("Client Request is accepted at port no: "+s.getPort());
        System.out.println("Server's Communication Port: "+s.getLocalPort());

        DataInputStream input=new DataInputStream(s.getInputStream());
        String str="";
        while (!str.equals("over")) {
            str=input.readUTF();
            System.out.println("Client Says: "+str);
        }
        s.close();
        input.close();
        sock.close();


    }
}
