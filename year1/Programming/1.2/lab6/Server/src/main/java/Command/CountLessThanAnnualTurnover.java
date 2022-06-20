package Command;

import Collection.Organization;
import Interface.Command;
import NET.Send;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayDeque;

public class CountLessThanAnnualTurnover implements Serializable, Command {
    String s;
    Double d;
    public CountLessThanAnnualTurnover(double d){
        this.d=d;
    }
    @Override
    public void strat(ArrayDeque<Organization> collection) throws IOException{
        int k = 0;
        for (Organization i : collection) {
            if (i.getAnnualTurnover() < d) {
                k += 1;
            }
        }
        s+=("Колличество организаций доход которых меньше чем " + d + " равно " + k);
        Send.send(s);
    }
}
