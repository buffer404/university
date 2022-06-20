package SQLCommand;

import Command.Msg;
import NET.DBHandler;
import NET.Receive;
import NET.Send;
import com.google.common.hash.Hashing;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddUser {
    private static final String ADD_USER_REQUEST = "INSERT INTO USERS (USERNAME, PASSWORD) VALUES (?, ?)";
    public static boolean addUser(String user, String password) {
        DBHandler.HandshakeBD();
        PreparedStatement addStatement = null;
        try {
            if (!CheckUser.checkUser(user, password)){
                addStatement = DBHandler.connection.prepareStatement(ADD_USER_REQUEST);
                addStatement.setString(1,user);
                addStatement.setString(2,Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString());
                addStatement.executeUpdate();
                addStatement.close();
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
