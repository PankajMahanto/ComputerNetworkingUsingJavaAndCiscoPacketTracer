// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.io.OutputStream;
// import java.net.HttpURLConnection;
// import java.net.URL;
// import java.nio.charset.MalformedInputException;

// public class post {
//         @SuppressWarnings("static-access")
//         public static void main(String[] args) throws MalformedInputException, IOException {

//                 @SuppressWarnings("deprecation")
//                 URL hp = new URL("https://jsonplaceholder.typicode.com/posts/");

//                 HttpURLConnection conn = (HttpURLConnection) hp.openConnection();

//                 conn.setRequestMethod("POST");
//                 conn.setDoOutput(true);

//                 OutputStream out = conn.getOutputStream();

//                 String pString = "Hi!!! We have posted something!!! Yay!!!";

//                 out.write(pString.getBytes());

//                 int resposeCode = conn.getResponseCode();

//                 System.out.println("Value of http created is:" + conn.HTTP_CREATED + "\n");

//                 if (resposeCode == conn.HTTP_CREATED) {
//                         System.out.println("This is the response code:" + resposeCode);
//                         System.out.println("This is the response msg from server: " + conn.getResponseMessage());
//                 } else {
//                         System.out.println("GO HOME EVERYBODY:");
//                 }

//                 InputStreamReader in = new InputStreamReader(conn.getInputStream());
//                 BufferedReader buffer = new BufferedReader(in);
//                 StringBuffer fromServer = new StringBuffer();

//                 String eachLine = null;
//                 while ((eachLine = buffer.readLine()) != null) {
//                         fromServer.append(eachLine);
//                         fromServer.append(System.lineSeparator());
//                 }
//                 buffer.close();
//                 System.out.println("Here is our posted content: " + fromServer);

//         }
// }



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostExample {
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/posts/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            OutputStream out = conn.getOutputStream();
            String pString = "Hi!!! We have posted something!!! Yay!!!";
            out.write(pString.getBytes());
            out.flush(); // Flush the output stream to ensure all data is sent

            int responseCode = conn.getResponseCode();
            // System.out.println("Value of http created is:" + HttpURLConnection.HTTP_CREATED + "\n");
            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                System.out.println("Post request successful!");
                System.out.println("This is the response code:"+ responseCode);
                System.out.println("This is the response msg from server: " + conn.getResponseMessage());

                InputStreamReader in = new InputStreamReader(conn.getInputStream());
                BufferedReader buffer = new BufferedReader(in);
                StringBuffer fromServer = new StringBuffer();
                String eachLine;
                while ((eachLine = buffer.readLine()) != null) {
                    fromServer.append(eachLine);
                    fromServer.append(System.lineSeparator());
                }
                buffer.close();
                System.out.println("Here is our posted content: " + fromServer);
            } else {
                System.out.println("Failed to make a post request. Response code: " + responseCode);
            }

            conn.disconnect(); // Close the connection
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
