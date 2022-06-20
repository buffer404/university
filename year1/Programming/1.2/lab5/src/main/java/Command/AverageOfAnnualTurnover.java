package Command;

import Org.Organization;

import java.util.ArrayDeque;

/**
 * Класс, используемый для вывода сренего значения доходов всех организаций в коллекции
 */
public class AverageOfAnnualTurnover {
    /**
     * @param a Коллекция организаций
     */
    private final ArrayDeque<Organization> array;

    public AverageOfAnnualTurnover(ArrayDeque<Organization> a) {
        array = a;
    }

    public void Show_verage_of_annual_turnover() {
        long r = 0;
        for (Organization i : array) {
            r += i.getAnnualTurnover();
        }
        try {
            System.out.println("Cреднее значение поля доходов для всех элементов коллекции = " + (r / array.size()));
        } catch (ArithmeticException e){
            System.out.println("Cреднее значение поля доходов для всех элементов коллекции = 0");
        }

    }

}
