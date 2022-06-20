package Command;

import Org.Organization;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Класс, используемый для вывода информации про все организации, которые присутствуют в коллекции
 */
public class Show {
    /**
     * @param a Коллекция организаций
     */

    private final ArrayDeque<Organization> array;

    public Show(ArrayDeque<Organization> a) {
        array = a;
    }

    public void Make_show() {
        ArrayList<Organization> arrayList = new ArrayList(array);
        Collections.sort(arrayList);
        for(Organization o : arrayList){
            System.out.println(o);
        }
    }

}