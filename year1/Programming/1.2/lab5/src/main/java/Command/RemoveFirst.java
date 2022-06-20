package Command;

import Org.Organization;

import java.util.ArrayDeque;

/**
 * Класс, используемый при удалении первого лемента коллекции
 */
public class RemoveFirst {

    private final ArrayDeque<Organization> array;

    public RemoveFirst(ArrayDeque<Organization> a) {
        array = a;
    }

    public void remove() {
        System.out.println("Удаление первого элемента");
        array.pollFirst();
    }
}
