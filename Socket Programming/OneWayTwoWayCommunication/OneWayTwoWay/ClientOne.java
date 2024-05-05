//package OneWayTwoWay;
// import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
// import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class ClientOne {



    public static void main(String[] args)throws IOException {
        Socket s=new Socket("localhost",5000);
        System.out.println("Client Connected at server HandShaking port "+s.getPort());
        System.out.println("Client Communication port: "+s.getLocalPort());
        System.out.println("Client is connected");
        System.out.println("Enter the message that you want to send and send over to close the connection: \n\n");

        DataOutputStream output=new DataOutputStream(s.getOutputStream());
        // BufferedReader read=new BufferedReader(new InputStreamReader(System.in));
        Scanner read=new Scanner(System.in);
        String str="";
        while (!str.equals("over")) {

            System.out.print("Client Says: ");
            // str=read.readLine(); This is using for bufferReader
            str=read.nextLine();
            output.writeUTF(str);
        }
        output.close();
        read.close();
        s.close();
    }
}