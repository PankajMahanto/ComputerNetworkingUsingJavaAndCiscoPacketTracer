
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ggg {
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        try {
            // Step 1: Create an instance of the URL class with the URL of the web page
            URL url = new URL("http://webcode.me/");

            // Step 2: Create an instance of the HttpURLConnection class
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Step 3: Enable the GET HTTP method
            conn.setRequestMethod("GET");

            // Step 4: Get the response code
            int responseCode = conn.getResponseCode();

            // Check for redirection (301 Moved Permanently)
            if (responseCode == HttpURLConnection.HTTP_MOVED_PERM) {
                String newUrl = conn.getHeaderField("Location");
                System.out.println("Redirecting to: " + newUrl);
                
                // Create a new URL object for the new location
                URL redirectedUrl = new URL(newUrl);

                // Open connection to the redirected URL
                HttpURLConnection redirectedConn = (HttpURLConnection) redirectedUrl.openConnection();
                redirectedConn.setRequestMethod("GET");

                // Get the response code from the redirected connection
                int redirectedResponseCode = redirectedConn.getResponseCode();

                // Check if redirection was successful
                if (redirectedResponseCode == HttpURLConnection.HTTP_OK) {
                    // Read and print the content of the redirected page
                    BufferedReader redirectedReader = new BufferedReader(new InputStreamReader(redirectedConn.getInputStream()));
                    StringBuilder redirectedContent = new StringBuilder();
                    String redirectedLine;
                    while ((redirectedLine = redirectedReader.readLine()) != null) {
                        redirectedContent.append(redirectedLine).append("\n");
                    }
                    redirectedReader.close();
                    System.out.println("Content of the redirected web page:");
                    System.out.println(redirectedContent.toString());
                } else {
                    System.out.println("Error: Failed to retrieve content from the redirected URL. Response Code: " + redirectedResponseCode);
                }
            } else if (responseCode == HttpURLConnection.HTTP_OK) {
                // Step 5: Print out the response code and response message from the web server
                System.out.println("Response Code: " + responseCode);
                System.out.println("Response Message: " + conn.getResponseMessage());

                // Step 6: Initialize an empty string to store the read contents
                StringBuilder content = new StringBuilder();

                // Step 7: Read the content from the connection
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    // Step 8: Append each line of the content to the initialized string with a newline character
                    content.append(line).append("\n");
                }
                reader.close();

                // Step 10: Print the content of the web page
                System.out.println("Content of the web page:");
                System.out.println(content.toString());
            } else {
                // Step 12: Print out an error message if response code is not OK or Moved Permanently
                System.out.println("Error: Failed to retrieve content. Response Code: " + responseCode);
                System.out.println("Response Message: " + conn.getResponseMessage());
            }

            // Step 14: Exit
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
