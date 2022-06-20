package NET;

import Serializator.Serializer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;

public class Receive {
    public static int ClientPort;
    public static InetAddress ClientAddress;
    public static Object receive () throws IOException, ClassNotFoundException {
        byte[] receivingDataBuffer = new byte[1024];
        DatagramPacket inputPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);
        Socket.serverSocket.receive(inputPacket);
        ClientPort = inputPacket.getPort();
        ClientAddress = inputPacket.getAddress();
        return Serializer.deserialize(inputPacket.getData());
    }
}
