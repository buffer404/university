package Command;

import Collection.Organization;
import Interface.Command;
import NET.Send;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayDeque;

public class AverageOfAnnualTurnover implements Serializable, Command {
    String s;
    @Override
    public void strat(ArrayDeque<Organization> collection) throws IOException, ClassNotFoundException {
        long r = 0;
        for (Organization i : collection) {
            r += i.getAnnualTurnover();
        }
        try {
            s+=("Cреднее значение поля доходов для всех элементов коллекции = " + (r / collection.size()));
        } catch (ArithmeticException e){
            s+=("Cреднее значение поля доходов для всех элементов коллекции = 0");
        }
        Send.send(s);
    }
}
