package NET;

import Serializator.Serializer;
import com.sun.jndi.cosnaming.IiopUrl;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Send {

    public static void send(Object o) throws IOException, ClassNotFoundException {
try{
    DatagramChannel check = DatagramChannel.open().bind(new InetSocketAddress(Init.ServerIP, Init.ServerPort));
    check.close();
    System.out.println("Cервер не доступен");
    }
        catch (IOException e){
            ByteBuffer buffer = ByteBuffer.wrap(Serializer.serialize(o));
            Init.Client.send(buffer, new InetSocketAddress(Init.ServerIP, Init.ServerPort));
            Receive.receive();
        }
    }
}
