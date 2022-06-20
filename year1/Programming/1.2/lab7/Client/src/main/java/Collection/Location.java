package Collection;

import java.io.Serializable;

public class Location implements Serializable{
    private Double x; //Поле не может быть null
    private int y;
    private Integer z; //Поле не может быть null
    private String name; //Поле может быть null

    /**
     *
     * @param x Координата города X
     * @param y Координата города Y
     * @param z Координата города Z
     * @param name Название города
     */
    public Location(Double x, int y, Integer z, String name){
        this.x=x;
        this.y=y;
        this.z=z;
        this.name=name;
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", name='" + name + '\'' +
                '}';
    }

    public Double getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Integer getZ() {
        return z;
    }

    public String getName() {
        return name;
    }
}

