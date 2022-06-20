package NET;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Socket {
    public static DatagramSocket serverSocket;
    public static int SERVICE_PORT;
    public Socket( int port){
        SERVICE_PORT = port;
        try {
            serverSocket = new DatagramSocket(SERVICE_PORT);
        } catch (IllegalArgumentException e) {
            System.err.println("Указан недопустимый порт");
            System.err.println("Перезапустите сервер и введите корректный порт");
            System.exit(0);
        }
        catch (SocketException e) {
            System.err.println("Установка соденинения не удалась");
            System.err.println("Перезапустите сервер и введите корректный порт");
            System.exit(0);
        }
    }


}
