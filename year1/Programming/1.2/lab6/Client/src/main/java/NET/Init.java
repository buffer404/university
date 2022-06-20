package NET;

import Command.Hello;
import Serializator.Serializer;

import javax.swing.text.html.HTMLEditorKit;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Init {
    public static int ClientPort;
    public static int ServerPort;
    public static InetAddress ServerIP;
    public static DatagramChannel Client;

    public Init(int PortServer){
        try {
            InetSocketAddress inetSocketAddress = new InetSocketAddress(InetAddress.getByName("localhost"), 0);
            Client = DatagramChannel.open().bind(inetSocketAddress);
        }
        catch (UnknownHostException e){
            System.err.println("Введен недействительный IP");
            System.err.println("Перезапустите программу и введите корректные данные");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(1);
            System.err.println("Не удалось подключится к сети");
            System.err.println("Перезапустите программу и введите корректные данные");
            e.printStackTrace();
        }

        try {
            ServerPort = PortServer;
            ServerIP = InetAddress.getByName("localhost");
            ByteBuffer buffer = ByteBuffer.wrap(Serializer.serialize(new Hello(ClientPort)));
            Client.send(buffer, new InetSocketAddress(Init.ServerIP, Init.ServerPort));
            try {
                Receive.receive();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (UnknownHostException e) {
            System.err.println("Введен недействительный IP");
            System.err.println("Перезапустите программу и введите корректные данные");
            System.exit(0);
        } catch (IOException e) {
            System.err.println("Не удалось подключится к серверу");
            System.err.println("Перезапустите программу и введите корректные данные");
            System.exit(0);
        }
    }
}
