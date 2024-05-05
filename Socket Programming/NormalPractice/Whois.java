package NormalPractice;

import java.net.*;
import java.io.*;

public class Whois {
    public static void main(String[] args) throws Exception {
        int c;
        
        //USING TRY BLOCK 
        try (Socket s = new Socket("whois.internetic.net", 43)) {

            InputStream in = s.getInputStream();
            OutputStream out = s.getOutputStream();

            String str = (args.length == 0 ? "OraclePressBooks.com" : args[0]) + "\n";

            byte buf[] = str.getBytes();
            out.write(buf);

            while ((c = in.read()) != -1) {
                System.out.println((char) c);
            }
        }


        //USING NORMAL THAT TIME NEED TO CLOSED SOCKET

        // Socket s = new Socket("whois.internetic.net", 43);

        // InputStream in = s.getInputStream();
        // OutputStream out = s.getOutputStream();

        // String str = (args.length == 0 ? "OraclePressBooks.com" : args[0]) + "\n";

        // byte buf[]= str.getBytes();
        // out.write(buf);

        // while ((c=in.read())!=-1) {
        //     System.out.println((char) c);
        // }

        // s.close();

    }
}
