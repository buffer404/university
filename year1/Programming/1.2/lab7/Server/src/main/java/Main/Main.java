package Main;

import Check.CheckData;
import NET.Credentials;
import NET.DBHandler;
import NET.Socket;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Подключаюсь к бд");
        try{
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException e){
            System.err.println("Драйвер не найден");
        }
        DBHandler.HandshakeBD();
        System.out.println("База данных успешно подключена");
        System.out.println("Введите порт");
        Socket socket = new Socket(CheckData.CheckInt());
        System.out.println("Жду клиентов");
        Work.work();
    }
}
