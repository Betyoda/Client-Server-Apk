import java.io.*;
import java.net.*;

public class SimpleServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server started. Waiting for client...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected: " + socket.getInetAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            String clientMessage, serverMessage;

            while (true) {
                // Read message from client
                clientMessage = in.readLine();
                if (clientMessage.equalsIgnoreCase("stop")) {
                    System.out.println("Client exited the chat.");
                    break;
                }
                System.out.println("Client: " + clientMessage);

                // Get response from server
                System.out.print("Server: ");
                serverMessage = userInput.readLine();
                out.println(serverMessage);

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