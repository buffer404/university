package NET;

import Main.Work;
import Serializator.Serializer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;

public class Receive {
    public static void receive() throws IOException, ClassNotFoundException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        SocketAddress remoteAdd = Init.Client.receive(buffer);
        Serializer.deserialize(buffer);
    }
}
