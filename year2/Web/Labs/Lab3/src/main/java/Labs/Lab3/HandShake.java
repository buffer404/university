package Labs.Lab3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.sql.*;

public class HandShake {
    private static final String jdbcURL = "jdbc:postgresql://pg:5432/studs"; //jdbc:postgresql://pg:5432/studs jdbc:postgresql://localhost:7470/studs
    private static String user;
    private static String password;
    private static Scanner data;
    private static Connection connection;

    public static Connection getConnection() {
        return connection;
    }

    public static void logIn(){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Где драйвер?");
        }

        try{
            data = new Scanner(new FileReader("/home/s307471/public_html/data.txt")); // /home/s307471/public_html/data.txt D:\Рабочий стол\ITMO\Web\Labs\data.txt
        } catch (FileNotFoundException e) {
            System.err.println("Не найден файл с данными");
            e.printStackTrace();
        }

        try {
            user = data.nextLine().trim();
            password = data.nextLine().trim();
        }catch (NoSuchElementException e){
            System.err.println("В файле нет данных");
        }
        handShake();
    }

    public static void handShake(){
        try{
            connection = DriverManager.getConnection(jdbcURL, user, password);
        } catch (SQLException throwables) {
            System.err.println("Не удолось подключится к бд");
            throwables.printStackTrace();
        }
    }

}
