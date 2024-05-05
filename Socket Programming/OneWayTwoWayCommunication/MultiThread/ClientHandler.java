

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

 class ClientHandler extends Thread {
    
    DateFormat forDate=new SimpleDateFormat("yyyy/MM/dd");
    DateFormat forTime=new SimpleDateFormat("hh:mm:ss");
    final Socket comThread;

    final DataInputStream disThread;
    final DataOutputStream dosThread;
    String received="";
    String toReturn="";

    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos){
        this.comThread=s;
        this.disThread=dis;
        this.dosThread=dos;
    }
    public void run (){
        while (true) {
            try {
                dosThread.writeUTF("What do you want [Date/Time]");
                received=disThread.readUTF();
                if(received.equals("Exit")){
                    System.out.println("Client "+this.comThread+ " sends exits");
                    System.out.println("Closing the connection");
                    this.comThread.close();
                    break;
                }

                Date date=new Date();
                switch (received) {
                    case "Date":
                    toReturn=forDate.format(date);
                    dosThread.writeUTF(toReturn);
                        break;

                    case "Time":
                    toReturn=forTime.format(date);
                    dosThread.writeUTF(toReturn);
                        break;
                
                    default:
                    dosThread.writeUTF("Invalid input");
                        break;
                }


            } catch (Exception e) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE,null,e);
            }
        }
        try {
            this.dosThread.close();
            this.disThread.close();
        } catch (Exception e) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE,null,e);
        }
    }
}
