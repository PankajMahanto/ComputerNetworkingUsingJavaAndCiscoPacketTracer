package Test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.System.Logger;
import java.net.Socket;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;


class ClientHandler extends Thread{
    private DataInputStream in;
    private DataOutputStream out;
    private Socket cSock;
    // DateFormat forDate=new SimpleDateFormat("yyyy/mm/dd");
    // DateFormat forTime=new SimpleDateFormat("hh:mm:ss");

    String rec="";
    String back="";
    public ClientHandler(Socket cSock){
        this.cSock=cSock;
        // this.in=in;
        // this.out=out;

        in=new DataInputStream(cSock.getInputStream());
        out=new DataOutputStream(cSock.getOutputStream(),true);

    }

    @Override
    public void run(){
        while(true){
            try {
                String str=in.readUTF();
                if(str.equalsIgnoreCase("Exit")){
                    System.out.println("Client "+getName()+" Disconnected");
                    this.cSock.close();
                    break;
                }
            
            String[] index=str.split(str);

            if(index.length>>=2){
                out.writeUTF("Invalid Input Try Again....");
                continue;
            }

            int num=Integer.parseInt(index[0]);

            Boolean result=CheckPrimeNumber(num);

            if(result) out.writeUTF("This number is Prime Number");
            else out.writeUTF("This Not number is Prime Number");

            

            } catch (IOException e) {
                
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE,null,e);
            }
        }

        try{
            this.in.close();
            this.out.close();
        }catch(IOException e){
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE,null,e);
        }
    }

    private Boolean CheckPrimeNumber(int num){
        if(num==0|| num==1) return false;
        for (int i=2;i*i<=num;i+=2){
            if(num%i==0) return false; 
        }
        return true;
    }

    private void colsedConnection() throws IOException{
        in.close();
        out.close();
        cSock.close();
    }
}

