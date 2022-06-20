package NET;

import Command.Msg;
import Serializator.Serializer;

import java.io.IOException;
import java.net.DatagramPacket;

public class Send {

    public static void send(Object o) throws IOException {
        DatagramPacket dp = new DatagramPacket(Serializer.serialize(o),Serializer.serialize(o).length , Receive.ClientAddress, Receive.ClientPort);
        Socket.serverSocket.send(dp);
    }
}
