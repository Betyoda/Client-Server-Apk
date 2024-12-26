import java.io.*;
import java.net.*;

public class SimpleClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345)) {
            System.out.println("Connected to server.");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            String serverMessage, clientMessage;

            while (true) {
                // Get input from user
                System.out.print("Client: ");
                clientMessage = userInput.readLine();
                out.println(clientMessage);

                if (clientMessage.equalsIgnoreCase("stop")) {
                    System.out.println("Client exited the chat.");
                    break;
                }

                // Read message from server
                serverMessage = in.readLine();
                System.out.println("Server: " + serverMessage);

                if (serverMessage.equalsIgnoreCase("stop")) {
                    System.out.println("Server exited the chat.");
                    break;
                }
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}