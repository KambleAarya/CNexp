import java.net.*;
import java.io.*;

public class UDPServer{
    public static void main(String[] args) {
        int port = 9876;  // Port number for the server

        try (DatagramSocket socket = new DatagramSocket(port)) {
            System.out.println("Server is listening on port " + port);

            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            // Listen for incoming datagrams
            while (true) {
                socket.receive(packet);  // Receive packet from client
                String message = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received from client: " + message);

                // Send an echo response to client
                String response = "Echo: " + message;
                byte[] responseBytes = response.getBytes();
                DatagramPacket responsePacket = new DatagramPacket(
                    responseBytes, responseBytes.length, packet.getAddress(), packet.getPort()
                );
                socket.send(responsePacket);  // Send response back to client
            }
        } catch (IOException ex) {
            System.out.println("Server error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
