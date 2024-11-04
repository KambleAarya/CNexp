import java.net.*;
import java.io.*;

public class UDPClient{
    public static void main(String[] args) {
        String hostname = "localhost";  // Server IP or hostname
        int port = 9876;  // Port number to connect to the server

        try (DatagramSocket socket = new DatagramSocket()) {
            byte[] buffer = "Hello, Server!".getBytes();

            // Send packet to the server
            InetAddress address = InetAddress.getByName(hostname);
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
            socket.send(packet);

            // Receive response from the server
            packet = new DatagramPacket(new byte[1024], 1024);
            socket.receive(packet);
            String response = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Server response: " + response);
        } catch (IOException ex) {
            System.out.println("Client error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}