package Main;
import Serializator.Serializer;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException { // Датаграммы
        Work work = new Work();
        work.start();
    }
}
