
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetExample {
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        try {
            // Create URL object with the target URL
            URL url = new URL("http://webcode.me/");
            
            // Open connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            // Set request method to GET
            connection.setRequestMethod("GET");
            
            // Get the response code
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            
            // Read response from the connection
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            
            // Print response
            System.out.println("Response Content:");
            System.out.println(response.toString());
            
            // Disconnect the connection
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

