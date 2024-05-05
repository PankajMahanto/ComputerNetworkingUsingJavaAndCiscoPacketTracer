package NormalPractice;

import java.net.*;

/**
 * InetAddressTest
 */
public class InetAddressTest {

    public static void main(String[] args) throws UnknownHostException {
        InetAddress address= InetAddress.getLocalHost();
        System.out.println(address);

        address=InetAddress.getByName("www.facebook.com");
        System.out.println(address);

        InetAddress st[]=InetAddress.getAllByName("www.nba.com");

        for(int i=0;i<st.length;i++){
            System.out.println(st[i]);
        }

    }
}