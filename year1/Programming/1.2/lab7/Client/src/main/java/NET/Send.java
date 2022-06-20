package NET;

import Serializator.Serializer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Send {

    public static Object send(Object o) throws IOException, ClassNotFoundException {
try{
    DatagramChannel check = DatagramChannel.open().bind(new InetSocketAddress(Init.ServerIP, Init.ServerPort));
    check.close();
    System.out.println("Cервер не доступен");
    }
        catch (IOException e){
            ByteBuffer buffer = ByteBuffer.wrap(Serializer.serialize(o));
            Init.Client.send(buffer, new InetSocketAddress(Init.ServerIP, Init.ServerPort));
            return Receive.receive();
        }
        return null;
    }
}
