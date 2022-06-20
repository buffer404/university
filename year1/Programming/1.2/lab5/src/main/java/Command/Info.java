package Command;

import Org.Organization;

import java.util.ArrayDeque;
import java.util.Date;

/**
 * Класс, используемый для вовода информации про коллекцию
 */
public class Info {
    /**
     * @param a Коллекция организаций
     * @param date Дата инициализации
     */

    private final ArrayDeque<Organization> array;
    private final Date date;

    public Info(ArrayDeque<Organization> a, Date date) {
        array = a;
        this.date = date;
    }

    public void Show_info() {
        System.out.println(array.getClass());
        System.out.println(array.size());
        System.out.println(date);
    }
}
