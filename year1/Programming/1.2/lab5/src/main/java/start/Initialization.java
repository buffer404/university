package start;

import Org.Organization;
import utility.Pharse;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Queue;
import java.util.Scanner;

/**
 * Класс, который подготавливает данные для работы
 */
public class Initialization {

    public static ArrayDeque<Organization> ArrayOrganizations;
    public static Pharse pharse;
    public static Queue<String> ArrayCommand;
    public static Date DateInitialization;

    /**
     * Класс, используемый для запуска программы
     * @param arrayOrganizations Список организаций
     * @param pharse Парсер
     * @param arrayCommand список последних комманд
     * @param dateInitialization Дата инициализации
     */
    public Initialization(ArrayDeque<Organization> arrayOrganizations, Pharse pharse, Queue<String> arrayCommand, Date dateInitialization) {
        ArrayOrganizations = arrayOrganizations;
        Initialization.pharse =pharse;
        ArrayCommand = arrayCommand;
        DateInitialization= dateInitialization;
    }
}
