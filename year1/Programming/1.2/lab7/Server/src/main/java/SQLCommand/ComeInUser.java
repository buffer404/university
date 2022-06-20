package SQLCommand;

import Command.Msg;
import NET.DBHandler;
import NET.Send;

import java.io.IOException;

public class ComeInUser {
    public static boolean comeInUser(String user, String password) {
        DBHandler.HandshakeBD();

        if (CheckUser.checkUser(user, password)) {
            return true;
        } else {
            return false;
        }
    }
}