package Command;

import Org.Organization;
import utility.Pharse;

import java.io.IOException;
import java.util.ArrayDeque;

/**
 * Класс, используемый для сохраниния коллекции в файл
 */
public class Save {
    /**
     * @param p Класс Pharse созданный для чтения/записи из файла
     * @param a коллекция организаций
     */

    private final Pharse pharse;
    private final ArrayDeque<Organization> array;

    public Save(Pharse p, ArrayDeque<Organization> a) {
        pharse = p;
        array = a;
    }

    public void Make_save() {
        try {
            pharse.write(array);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Коллекция успешно сохранена!");
    }

}
