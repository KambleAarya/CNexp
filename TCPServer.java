import java.io.*;
import java.net.*;

public class TCPServer{
    public static void main(String[] args) {
        int port = 6789;  // Port number for the server

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                // Accept incoming client connections
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                // Create input and output streams for client communication
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);

                // Read and echo message from the client
                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.println("Received from client: " + message);
                    writer.println("Echo: " + message);  // Send response back to client
                }

                socket.close();  // Close the client connection after message exchange
            }
        } catch (IOException ex) {
            System.out.println("Server error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}