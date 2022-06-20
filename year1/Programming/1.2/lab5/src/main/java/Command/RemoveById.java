package Command;

import Org.Organization;

import java.sql.SQLOutput;
import java.util.ArrayDeque;

/**
 * Класс, используемый для измения данных об организации
 */
public class RemoveById {

    private final int id;
    private final ArrayDeque<Organization> array;


    public RemoveById(int id, ArrayDeque<Organization> a) {
        this.id = id;
        array = a;
    }

    public void remove() {
        System.out.println("Удаление элемента " + id);
        Organization[] organizations = array.toArray(new Organization[0]);
        array.clear();
        for (Organization i : organizations) {
            if (i.getId() != id) {
                array.add(i);
            }
        }
        System.out.println("Организация успешно удалена из коллекции");
    }

}
