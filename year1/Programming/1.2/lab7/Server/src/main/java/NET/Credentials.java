package NET;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Credentials {
    public static String user;
    public static String password;
    static {
        try {
            Scanner scanner = new Scanner(new FileReader("D:\\Рабочий стол\\ITMO\\Programming\\1.2\\lab7\\Server\\src\\main\\resources\\Credentials.txt")); // C:\Users\Леонид\Desktop\ITMO\Programming\1.2\lab7\Server\src\main\resources\Credentials.txt
            user=scanner.nextLine().trim();
            password=scanner.nextLine().trim();
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден");
        }
        catch (NoSuchElementException e){
            System.err.println("В файле не нвйдены данные для входа");
        }
    }
}
