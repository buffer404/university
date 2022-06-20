package SQLCommand;

import NET.DBHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CheckUpdate {

    private static final String CHECK_USER_REQUEST = "SELECT * FROM org WHERE idorg = ? AND owner = ?";
    public static boolean checkUpdate(String user, int id){
        DBHandler.HandshakeBD();
        PreparedStatement addStatement = null;
        try {
            addStatement = DBHandler.connection.prepareStatement(CHECK_USER_REQUEST);
            addStatement.setInt(1, id);
            addStatement.setString(2,user);
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
