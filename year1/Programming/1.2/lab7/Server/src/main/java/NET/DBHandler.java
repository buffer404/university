package NET;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler {
    private static String url = "jdbc:postgresql://localhost:7471/studs"; // jdbc:postgresql://pg:5432/studs  jdbc:postgresql://localhost:7471/studs
    public static Connection connection;
    public static void HandshakeBD(){
        boolean enter = false;
        while (!enter){
            try {
                connection= DriverManager.getConnection(url,Credentials.user,Credentials.password);
                break;
            } catch (SQLException throwables) {
                enter = false;
            }
        }
    }
}
