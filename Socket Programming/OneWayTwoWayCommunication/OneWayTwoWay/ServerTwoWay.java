//package OneWayTwoWay;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerTwoWay {
    public static void main(String[] args) throws IOException{
        ServerSocket sock=new ServerSocket(5000);
        System.out.println("Server is connected at port no: "+sock.getLocalPort());
        System.out.println("Server is connected\n");
        System.out.println("Waiting for the Client\n");

        Socket s=sock.accept();
        System.out.println("Client Request is accepted at port no: "+s.getPort());
        System.out.println("Server's Communication Port: "+s.getLocalPort());

        //Create Streams for both sending and receiving data
        DataInputStream input=new DataInputStream(s.getInputStream());
        DataOutputStream output=new DataOutputStream(s.getOutputStream());

        Scanner scanner=new Scanner(System.in);
        String str="";
        while (!str.equals("over")) {
            //Read data from the client and print it
            str=input.readUTF();
            System.out.println("Client Says: "+str);

            //Send a response to the client
            // String serverRes="Server Response: "+str.toUpperCase();
            // output.writeUTF(serverRes);

            //Response the msg from server and send to client
            System.out.print("Server Says: ");
            String serverRes=scanner.nextLine();
            output.writeUTF(serverRes);
        }
        //closed Stream and Socket
        s.close();
        input.close();
        output.close();
        sock.close();
        scanner.close();


    }
}
