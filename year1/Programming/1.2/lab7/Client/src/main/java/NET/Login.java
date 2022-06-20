package NET;

import Check.CheckData;
import Command.Log;
import Command.Msg;

import java.io.IOException;

public class Login {
    public static String user;
    public static String password;
    public static boolean k; // новый или авторизованный
    public static boolean in = false; // корректный данные

    public static boolean login(boolean k) {
        try {
            System.out.println("Введите наш никнейм");
            user = CheckData.CheckNull();
            System.out.println("Введите пароль");
            password = CheckData.CheckNull();
            Log log = new Log(user, password, k);
            Msg msg = (Msg) Send.send(log);
            if(msg.i){
                return true;
            }
            else {
                return false;
            }
        } catch (IOException e) {
            System.err.println("Отправка данных не удалась");
        } catch (ClassNotFoundException e) {
            System.err.println("Класс не найден");
        }
        catch (NullPointerException e){
            return false;
        }
        return false;
    }
    public static boolean login(String user, String password, boolean k){
        return true;
    }
}
