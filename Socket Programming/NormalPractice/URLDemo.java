package NormalPractice;


import java.net.*;
public class URLDemo {
    @SuppressWarnings("deprecation")
    public static void main(String[] args)throws MalformedURLException {
        
        URL hp=new URL("https://www.google.com/youtube/");
        
        System.out.println("Protocol: "+hp.getProtocol());
        System.out.println("Port:"+hp.getPort());

        System.out.println("Host: "+hp.getHost());
        System.out.println("File:"+hp.getFile());
        System.out.println("EXT: "+hp.toExternalForm());
       
    }
}
