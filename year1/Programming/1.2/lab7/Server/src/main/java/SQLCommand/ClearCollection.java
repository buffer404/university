package SQLCommand;

import Collection.Organization;
import NET.DBHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayDeque;

public class ClearCollection {
    public final static String CLEAR_FOR = "DELETE FROM org WHERE owner =?";
    public final static String CLEAR_ID = "DELETE FROM org WHERE idorg =?";
    public static void clearCollection (String user) throws SQLException {
        DBHandler.HandshakeBD();
        PreparedStatement addStatement = DBHandler.connection.prepareStatement(CLEAR_FOR);
        addStatement.setString(1, user);
        addStatement.executeUpdate();
        addStatement.close();
        System.out.println("Список организаций успешно очищен");
    }
    public static void clearId(int id) throws SQLException{
        DBHandler.HandshakeBD();
        PreparedStatement addStatement = DBHandler.connection.prepareStatement(CLEAR_ID);
        addStatement.setInt(1, id);
        addStatement.executeUpdate();
        addStatement.close();
    }
}
