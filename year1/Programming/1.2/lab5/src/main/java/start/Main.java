package start;

import com.google.gson.JsonSyntaxException;
import utility.Pharse;
import Org.*;

import java.io.IOException;
import java.util.*;

/**
 * Запуск
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Здравствуйте, пожалуйста, укажите путь к хранению коллекции организаций");
        boolean normal = true;
        Date DateInitialization = new Date();
        ArrayDeque<Organization> ArrayOrganizations = new ArrayDeque<>();
        Queue<String> ArrayCommand = new LinkedList<>();
        Pharse pharse = null;
        Scanner scanner = null;
        while (normal) {
            scanner = new Scanner(System.in);
            try {
                pharse = new Pharse(scanner.nextLine()); // C:\Users\Леонид\Desktop\ITMO\Programming\1.2\lab5\src\main\resources\Info.json

                try {
                    ArrayOrganizations = pharse.read();
                    normal = false;
                } catch (JsonSyntaxException | NumberFormatException | NoSuchElementException e) {
                    System.err.println("Некорректное хранение данных в данном файле!");
                    System.err.println("Пожалуйста, укажите путь к другому файлу");
                } catch (IOException e){
                    System.err.println("Некорректный путь к файлу");
                }
            } catch (NoSuchElementException e) {
                System.err.println("Неверный путь до файла!");
            }
        }
        Initialization initialization = new Initialization(ArrayOrganizations, pharse, ArrayCommand, DateInitialization);
        Init init = new Init(scanner);
        init.init_go();
    }
}
