import java.io.*;
import java.net.*;

public class TCPClient{
    public static void main(String[] args) {
        String hostname = "localhost";  // Server IP or hostname
        int port = 6789;  // Port number to connect to the server

        try (Socket socket = new Socket(hostname, port)) {
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            // Send a message to the server
            writer.println("Hello, Server!");
            System.out.println("Server response: " + reader.readLine());  // Read server response

            socket.close();  // Close connection after sending the message
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}