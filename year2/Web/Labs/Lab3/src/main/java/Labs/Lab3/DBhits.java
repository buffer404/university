package Labs.Lab3;

import javax.faces.context.FacesContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBhits {
    public static String ADD = "INSERT INTO hits (x, y, r, id) VALUES (?, ?, ?, ?)";
    public static String LOAD = "SELECT * FROM hits WHERE id = ?";
    public static String DELETE = "DELETE FROM hits WHERE id = ?";

    public static void addDB(Hits hit, String id) throws SQLException {
         HandShake.logIn();
         PreparedStatement ps =  HandShake.getConnection().prepareStatement(ADD);
         ps.setDouble(1, hit.getX());
         ps.setDouble(2, hit.getY());
         ps.setDouble(3, hit.getR());
         ps.setString(4, id );
         ps.executeUpdate();
         ps.close();
         System.out.println("Добавлено");
    }

    public static ArrayList<Hits> loadDB(String id) throws SQLException {
        ArrayList<Hits> arrayList = new ArrayList<>();
        PreparedStatement ps = HandShake.getConnection().prepareStatement(LOAD);
        ps.setString(1, id);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            Hits hit = new Hits(resultSet.getDouble("x"), resultSet.getDouble("y"),resultSet.getDouble("r"));
            arrayList.add(hit);
            System.out.println(hit.toString());
        }
        ps.close();
        return arrayList;
    }
    public static void ClearDB(String id){
        try {
            PreparedStatement ps = HandShake.getConnection().prepareStatement(DELETE);
            ps.setString(1, id);
            ps.executeUpdate();
            ps.close();
            System.out.println("БД успешно очищенна");
        } catch (SQLException throwables) {
            System.err.println("Не удалось удалить данные из бд");
        }
    }
}
