package Interface;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayDeque;

public class ClearId {
    public final static String CLEAR_FOR = "DELETE FROM org WHERE owner =? AND idorg = ?";
    public static void clearCollection (String user, int id) throws SQLException {
        DBHandler.HandshakeBD();
        PreparedStatement addStatement = DBHandler.connection.prepareStatement(CLEAR_FOR);
        addStatement.setString(1, user);
        addStatement.setInt(2, id);
        addStatement.executeUpdate();
        addStatement.close();
    }
}
