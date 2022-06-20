package Command;

import Org.Organization;

import java.util.ArrayDeque;
/**
 * Класс, используемый для очистки коллекции организаций
 */
public class Clear {
    /**
     *
     * @param a Коллекция организаций
     */
    private final ArrayDeque<Organization> array;

    public Clear(ArrayDeque<Organization> a){
        array=a;
    }

    public void Make_clear(){
        array.clear();
        System.out.println("Коллекция успешно очещенна!");
    }

}
