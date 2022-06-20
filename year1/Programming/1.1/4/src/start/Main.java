package start;

import Except.NameException;
import cls.*;
import utility.*;

public class Main {

    public static void main(String[] args) {
        LoseWeight loseWeight = new LoseWeight(Weight.LOSEWEIGHT);
        HaveWeight haveWeight = new HaveWeight(null);
        Znaika znaika = new Znaika("Знайка", Place.MOON);  //Unchecked NullException
        Rocket rocket = new Rocket("Ракета", Place.MOON);
        Cord cord = new Cord("шнур");  //Checked InfoException

        try {
            loseWeight.info();
            haveWeight.info();
        }
        catch (NameException e){
            e.printStackTrace();
        }
        znaika.notice(rocket.normal());
        try {
            znaika.pull(cord.info());
        }
        catch (NameException e){
            e.printStackTrace();
        }
        znaika.go("лунной дорожке");
        rocket.normal();
        rocket.ChangePosition("горизонтальное");
        rocket.start();
        rocket.fly(rocket.getName(), rocket.getPlace());

    }
}
