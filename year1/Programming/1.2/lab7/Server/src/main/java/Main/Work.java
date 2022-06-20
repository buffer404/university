package Main;
import Collection.Organization;
import Interface.Command;
import NET.Receive;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import SQLCommand.LoadCollection;
import Command.Clear;
import Command.Show;
import Threads.ReceiveThread;

public class Work {
    public static HashMap PortUser = new HashMap();
    public static ArrayDeque<Organization> collection;
    public static ExecutorService es = Executors.newFixedThreadPool(50);
    public static void work(){
        try {
            while (true) {
                Object o = Receive.receive();

                if (PortUser.containsKey(Receive.ClientPort)){
                    if (o.getClass() == Clear.class || o.getClass() == Show.class){
                        ArrayDeque<Organization> arrayDeque = LoadCollection.loadCollection((String) PortUser.get(Receive.ClientPort));
                        ReceiveThread rt = new ReceiveThread((Command) o,  (String) PortUser.get(Receive.ClientPort), arrayDeque);
                        rt.start();
                    }
                    else {
                        ArrayDeque<Organization> arrayDeque = LoadCollection.loadCollection();
                        ReceiveThread rt = new ReceiveThread((Command) o,  (String) PortUser.get(Receive.ClientPort), arrayDeque);
                        rt.start();
                    }
                }
                else {
                    Command command = (Command) o;
                    ReceiveThread rt = new ReceiveThread((Command) o,  null, null);
                    rt.start();
                }

            }
        } catch (IOException e) {
            System.err.println("Ошибка в сети");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Класс не найдет");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}