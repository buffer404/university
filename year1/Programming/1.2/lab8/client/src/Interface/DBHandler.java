package Interface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBHandler {
    private static String url;
    private static String user;
    private static String password;
    public static Connection connection;

    public DBHandler(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public static void HandshakeBD() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Здорово отец, база данных успешно подключена");
        } catch (SQLException throwables) {
            System.err.println("Не удалось подключится к базе данных");
        }
    }

}

