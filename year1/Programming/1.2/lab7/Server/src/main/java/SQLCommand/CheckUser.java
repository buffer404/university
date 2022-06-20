package SQLCommand;

import NET.DBHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckUser {
    private static final String CHECK_USER_REQUEST = "SELECT * FROM USERS WHERE username =?";
    public static boolean checkUser(String user, String password){
        DBHandler.HandshakeBD();
        PreparedStatement addStatement = null;

        try {
            addStatement = DBHandler.connection.prepareStatement(CHECK_USER_REQUEST);
            addStatement.setString(1, user);
            if(addStatement.executeQuery().next()){
                return true;
            }
            else {
                return false;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }
}
