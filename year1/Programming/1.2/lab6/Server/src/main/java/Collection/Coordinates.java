package Collection;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private int x; //Значение поля должно быть больше -70
    private Integer y; //Поле не может быть null
    public Coordinates(){
        x=0;
        y=0;
    }

    public Coordinates(int x, Integer y){
        this.x=x;
        this.y=y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

