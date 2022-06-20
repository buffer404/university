package Command;

import Org.Organization;

import java.util.ArrayDeque;

/**
 * Класс, который используется для вывода количкства организаций, чей доход меньше введенного
 */
public class CountLessThanAnnualTurnover {
    /**
     * @param price Заданная цена
     * @param a Коллекция организаций
     */

    private final int price;
    private final ArrayDeque<Organization> array;

    public CountLessThanAnnualTurnover(int price, ArrayDeque<Organization> a) {
        array = a;
        this.price = price;
    }

    public void Show_count_less_than_annual_turnover() {
        int k = 0;
        for (Organization i : array) {
            if (i.getAnnualTurnover() < price) {
                k += 1;
            }
        }
        System.out.println("Колличество организаций доход которых меньше чем " + price + " равно " + k);
    }

}
