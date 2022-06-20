package Command;

import Org.Organization;

import java.util.*;

/**
 * Класс, используемый при удалении из коллекции тех организаций, чей доход меньше чем данный
 */
public class RemoveLower {
    /**
     * @param price Минимальный доход
     * @param a Коллекция организаций
     */

    private final int price;
    private final ArrayDeque<Organization> array;


    public RemoveLower(int price, ArrayDeque<Organization> a) {
        this.price = price;
        array = a;
    }

    public void remove() {
        for (Organization o : array){
            if (o.getAnnualTurnover()<price){
                array.removeFirstOccurrence(o);
            }
        }
        System.out.println("Все организации, доход которых ниже чем " + price + " успешно удалены из списка!");
    }
}
