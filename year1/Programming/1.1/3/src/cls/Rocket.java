package cls;

import utility.Simple;
import utility.AbstractPlace;

import java.util.Objects;

public class Rocket extends AbstractPlace implements Simple{
    private String name;
    private String place;
    public Rocket(String name, String place){
        this.name=name;
        this.place=place;
    }

    @Override
    public void getName() {
        System.out.print(name);
    }

    @Override
    public void setName(String name) {
        this.name=name;
    }

    @Override
    public void getPlace() {
        System.out.print(place);
    }

    @Override
    public void setPlace(String place) {
        this.place=place;
    }

    public void ChangePosition(String s){
        getName();
        System.out.print(" приняла "+s+" положение");
    }

    public void fly(){
        System.out.print(", поплыла над поверхностью ");
        getPlace();
    }

    public void jump(){
        System.out.println("");
        getName();
        System.out.print(" опускалась, но, едва коснувшись поверхности ");
        getPlace();
        System.out.print(", отталкивалась от нее и снова поднималась вверх.");
    }

    @Override
    public String toString() {
        return "Rocket{" +
                "name='" + name + '\'' +
                ", place='" + place + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rocket rocket = (Rocket) o;
        return Objects.equals(name, rocket.name) &&
                Objects.equals(place, rocket.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, place);
    }
}
