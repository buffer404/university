package NET;

import Command.Hello;
import Command.Msg;
import Serializator.Serializer;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Init {
    public static int ClientPort;
    public static int ServerPort;
    public static InetAddress ServerIP;
    public static DatagramChannel Client;

    public static boolean init(int PortServer){
        try {
            ServerSocket s = new ServerSocket(0);
            InetSocketAddress inetSocketAddress = new InetSocketAddress(InetAddress.getByName("localhost"), s.getLocalPort());
            Client = DatagramChannel.open().bind(inetSocketAddress);
        }
        catch (UnknownHostException e){
            System.err.println("Введен недействительный IP");
            System.err.println("Перезапустите программу и введите корректные данные");
            System.exit(0);
        } catch (IOException e) {
            System.err.println("Не удалось подключится к сети");
            System.err.println("Перезапустите программу и введите корректные данные");
            e.printStackTrace();
        }


        try{
            ServerSocket s = new ServerSocket(PortServer);
            InetSocketAddress inetSocketAddress = new InetSocketAddress(InetAddress.getByName("localhost"), s.getLocalPort());
            Client = DatagramChannel.open().bind(inetSocketAddress);
        }
        catch (BindException ee){
            try {
                ServerPort = PortServer;
                ServerIP = InetAddress.getByName("localhost");
                ByteBuffer buffer = ByteBuffer.wrap(Serializer.serialize(new Hello(ClientPort)));
                Client.send(buffer, new InetSocketAddress(Init.ServerIP, Init.ServerPort));
                try {
                    Msg msg = (Msg) Receive.receive();
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (UnknownHostException e) {
                System.err.println("Введен недействительный IP");
                System.err.println("Перезапустите программу и введите корректные данные");
                System.exit(0);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Не удалось подключится к серверу");
                System.err.println("Перезапустите программу и введите корректные данные");
                System.exit(0);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
return false;

    }
}
