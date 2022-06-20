package NET;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.channels.DatagramChannel;

public class DatagramClient {
    public static InetAddress ip;

    static {
        try {
            ip = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static InetSocketAddress Clientaddress = new InetSocketAddress(ip , 1336);

    public static DatagramChannel client;
    public static int ServerPort = 5852;
    static {
        try {
            client = DatagramChannel.open().bind(Clientaddress);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
