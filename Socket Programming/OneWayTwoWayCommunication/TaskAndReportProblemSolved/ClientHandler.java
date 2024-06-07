package TaskAndReportProblemSolved;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
// import Practice.MultithreadedServer;
// import Server.MultithreadedServer;

class ClientHandler extends Thread {

    final Socket comThread;
    final DataInputStream disThread;
    final DataOutputStream dosThread;
    
    String received="";
    String toReturn="";

    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) throws IOException{
        this.comThread=s;
        this.disThread=dis;
        this.dosThread=dos;
    }

    // public ClientHandler(Socket s)  throws IOException{
    //     this.comThread = s;
    //     this.disThread = new DataInputStream(s.getInputStream());
    //     this.dosThread = new DataOutputStream(s.getOutputStream());
    // }

    public void run() {
        try {
            while (true) {
                String received = disThread.readUTF();

                if (received.equals("Exit")) {
                    System.out.println("Client " + this.comThread + " sends exits");
                    System.out.println("Closing the connection");
                    MultithreadedServer.clientCount.decrementAndGet();
                    this.comThread.close();
                    break;
                }

                String uppercase = received.toUpperCase();
                dosThread.writeUTF(uppercase);
            }
        } catch (Exception e) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                this.dosThread.close();
                this.disThread.close();
            } catch (Exception e) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}
