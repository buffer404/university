package NET;

import Command.Msg;
import Serializator.Serializer;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;

public class Receive {
    public static Msg receive() throws IOException, ClassNotFoundException {
        ByteBuffer buffer = ByteBuffer.allocate(2048);
        SocketAddress remoteAdd = Init.Client.receive(buffer);
        Msg msg = Serializer.deserialize(buffer.array());
        return msg;
    }
}
