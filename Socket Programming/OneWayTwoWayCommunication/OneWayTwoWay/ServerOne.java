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


        //DataInput Stream er through te asole kono kisu read kora hoy jeta stream er through te ase
        //jemon port er through te jeta ase
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
