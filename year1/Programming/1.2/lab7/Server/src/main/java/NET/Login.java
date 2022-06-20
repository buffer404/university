package NET;

import Check.CheckData;
import Command.Hello;
import Command.Log;
import Main.Work;
import SQLCommand.AddUser;
import SQLCommand.ComeInUser;

import java.io.IOException;
import java.net.InetAddress;

public class Login {

    public static boolean login(String user, String password, boolean k) throws IOException, ClassNotFoundException {
        if (k) { // авторизация
            if (ComeInUser.comeInUser(user, password)) {
                Work.PortUser.put(Receive.ClientPort, user);
                return true;
            }
        } else { // новый
            if (AddUser.addUser(user, password)) {
                Work.PortUser.put(Receive.ClientPort, user);
                return true;
            }
        }
        return false;
    }
}
