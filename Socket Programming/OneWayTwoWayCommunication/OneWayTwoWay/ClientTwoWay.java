//package OneWayTwoWay;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientTwoWay {
    public static void main(String[] args)throws IOException {
        Socket s=new Socket("localhost",5000);
        System.out.println("Client Connected at server HandShaking port "+s.getPort());
        System.out.println("Client Communication port: "+s.getLocalPort());
        System.out.println("Client is connected");
        System.out.println("Enter the message that you want to send and send over to close the connection: ");

        DataOutputStream output=new DataOutputStream(s.getOutputStream());
        BufferedReader read=new BufferedReader(new InputStreamReader(System.in));
        DataInputStream input=new DataInputStream(s.getInputStream());
        String str="";
        while (!str.equals("over")) {
            //Read input from the user and send it to the server
            System.out.print("Client Says: ");
            str=read.readLine();
            output.writeUTF(str);

            //Read data from the server and print it
            String serverRes=input.readUTF();
            System.out.println("Server Says: "+serverRes);
        }
        //closed Stream and Socket
        output.close();
        read.close();
        input.close();
        s.close();
    }
}
