package cls;

import Except.ConstructException;
import utility.Place;
import utility.PlaceI;
import utility.SimpleI;

import java.util.Objects;

public class Rocket implements SimpleI, PlaceI {

    private String name;
    private Place place;

    public Rocket(String name, Place place){
        if (name == null | place == null){
            throw new ConstructException();
        }
        this.name=name;
        this.place=place;
    }

    class SPlace{
        public SPlace(Place p){
            place=p;
        }
    }

    public void ChangePosition(String s){
        System.out.println(getName()+", поменяла положение на "+s);
    }

    public String normal(){
        return getName()+" поднялась на достаточную высоту";
    }

    public void start(){
        System.out.println(getName()+", послушно поплыла над поверхностью "+place.getInfo());
    }

    static class Fly{
        public Fly(String name, String place){
            System.out.println(name+ " по временам опускалась, но, едва коснувшись поверхности "+place+", отталкивалась от нее и снова поднималась вверх.");
        }
    }

    public void fly(String name, String place){
        Fly f = new Fly(name, place);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String s) {
        this.name=s;
    }

    @Override
    public String getPlace() {
        return place.getInfo();
    }

    @Override
    public void setPlace(Place place) {
        SPlace sPlace = new SPlace(place);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rocket rocket = (Rocket) o;
        return Objects.equals(name, rocket.name) &&
                place == rocket.place;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, place);
    }

    @Override
    public String toString() {
        return "Rocket{" +
                "name='" + name + '\'' +
                ", place=" + place +
                '}';
    }
}
