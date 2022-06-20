package Command;

import Org.Organization;

import java.util.ArrayDeque;

/**
 * Класс, используемый для вывода всех полных названий организаций
 */
public class PrintUniqueFullName {
    /**
     * @param a коллекция организаций
     */

    private final ArrayDeque<Organization> array;

    public PrintUniqueFullName(ArrayDeque<Organization> a) {
        array = a;
    }

    public void Show_full_name() {
        for (Organization i : array) {
            System.out.println(i.getFullName());
        }
    }

}
