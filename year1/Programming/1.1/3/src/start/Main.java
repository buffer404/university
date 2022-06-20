package start;

import cls.*;
import utility.Position;

public class Main {

    public static void main(String[] args) {
        Znaika znaika = new Znaika("Знайка","Луны");
        Rocket rocket = new Rocket("Ракета","Луны");
        HaveWeight haveWeight = new HaveWeight("Предметы, имеющие вес", (byte) 1);
        LoseWeight loseWeight= new LoseWeight("Предметы, теряющие вес", (byte) -1);
        Spring spring = new Spring();
        Cord cord = new Cord();
        MoonRoad moonRoad = new MoonRoad();
        Position position = Position.HORIZONTALLY;

        loseWeight.Info(spring.info());
        haveWeight.Info("");
        znaika.notice(" что ракета поднялась на достаточную высоту,");
        znaika.pull(cord.info());
        znaika.go(moonRoad.info());
        rocket.ChangePosition(position.getInfo());
        rocket.fly();
        rocket.jump();

    }
}
