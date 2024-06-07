

import java.io.*;
import java.net.*;

public class Client {
    public static final String HOST = "localhost"; // Server hostname (replace with server IP if needed)
    public static final int PORT = 1234;

    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket(HOST, PORT);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("Enter two integers and an operator (Sum, Subtract, Multiplication, Division, Modules) or 'ENDS' to quit:");
            String message = userInput.readLine();

            out.println(message);

            if (message.equalsIgnoreCase("ENDS")) {
                break;
            }

            String response = in.readLine();
            System.out.println("Server response: " + response);
        }

        System.out.println("Client disconnected.");
        closeConnections(in, out, clientSocket);
    }

    private static void closeConnections(BufferedReader in, PrintWriter out, Socket clientSocket) throws IOException {
        in.close();
    }

}