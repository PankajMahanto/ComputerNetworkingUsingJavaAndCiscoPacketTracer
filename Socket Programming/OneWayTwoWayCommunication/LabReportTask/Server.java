import java.io.*;
import java.net.*;

public class Server {
    public static final int PORT = 1234; // Port to listen on
    public static final int MAX_CLIENTS = 5; // Maximum number of clients to serve

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        int clientCount = 0;

        while (clientCount < MAX_CLIENTS) {
            System.out.println("Waiting for client connection...");
            Socket clientSocket = serverSocket.accept();
            clientCount++;

            System.out.println("Client " + clientCount + " connected!");

            // Thread to handle each client connection
            new ClientHandler(clientSocket).start();
        }

        System.out.println("Maximum client limit reached. Server shutting down.");
        serverSocket.close();
    }
}

class ClientHandler extends Thread {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            while (true) {
                String message = in.readLine();

                if (message.equalsIgnoreCase("ENDS")) {
                    System.out.println("Client " + getName() + " disconnected.");
                    break;
                }

                String[] parts = message.split(" ");
                if (parts.length != 3) {
                    out.println("Invalid input. Please send two integers and an operator (Sum, Subtract, Multiplication, Division, Modules).");
                    continue;
                }

                int num1 = Integer.parseInt(parts[0]);
                int num2 = Integer.parseInt(parts[1]);
                String operator = parts[2];

                int result = performOperation(num1, num2, operator);
                out.println(result);
            }
        } catch (IOException e) {
            System.err.println("Error communicating with client: " + e.getMessage());
        } finally {
            try {
                closeConnections();
            } catch (IOException e) {
            
                e.printStackTrace();
            }
        }
    }

    private int performOperation(int num1, int num2, String operator) {
        switch (operator) {
            case "Sum":
                return num1 + num2;
            case "Subtract":
                return num1 - num2;
            case "Multiplication":
                return num1 * num2;
            case "Division":
                if (num2 == 0) {
                    return -1; // Handle division by zero
                }
                return num1 / num2;
            case "Modules":
                return num1 % num2;
            default:
                return -1; // Invalid operator
        }
    }

    private void closeConnections() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
}
