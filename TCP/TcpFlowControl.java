import java.util.Random;
import java.util.Scanner;

public class TcpFlowControl {

    public static int generateFrame(int winSize){
        Random random=new Random();
        int i,noOfGeneratedFrame;
        noOfGeneratedFrame=random.nextInt(500)%winSize;

        if(noOfGeneratedFrame==0) return winSize;
        else return noOfGeneratedFrame;
    }

    public static int generateAck(int noOfSent){
        Random random=new Random();
        int i,noOfAckFrame;
        noOfAckFrame=random.nextInt(500)%noOfSent;
        return noOfAckFrame;
    }
    public static void main(String[]arg){
        int noOfFrame,winSize,startByte=0,endByte=0,noOfAck=0,noOfSent=0;

        Scanner input=new Scanner(System.in);
        System.out.println("Enter the Total no of frame: ");
        noOfFrame=input.nextInt();
        System.out.println("Enter the Window size: ");
        winSize=input.nextInt();
        input.close();
        int dueFrame=noOfFrame;

        while(dueFrame>=0){

            noOfSent=generateFrame(winSize);
            endByte+=noOfSent;
            if(endByte>noOfFrame)
                endByte=noOfFrame;
            for(int i=startByte+1;i<=endByte;i++){
                System.out.println("Sending Frame: "+i);
            }
            noOfAck=generateAck(noOfSent);
            startByte+=noOfAck;
            if(startByte>noOfFrame)
                startByte=noOfFrame;
            System.out.println("Acknowlegment for the frame upto "+startByte);
            dueFrame-=noOfAck;
            endByte=startByte;
        }

        System.out.println("\nThe Sliding Window Protocol concludes here.");
    }
}