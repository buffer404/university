package cls;

import Except.ConstructException;
import utility.Place;
import utility.PlaceI;
import utility.SimpleI;

import java.util.Objects;


public class Znaika implements SimpleI, PlaceI {

    private String name;
    private Place place;

    public Znaika(String name, Place place){
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

    public void  notice(String s){
        System.out.println(getName()+", заметил, что "+s);
    }

    public void pull(String s){
        System.out.println(getName()+", потянул за "+s);
    }

    public void go(String s){
        System.out.println(getName()+", зашагал по "+s);
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
        Znaika znaika = (Znaika) o;
        return Objects.equals(name, znaika.name) &&
                place == znaika.place;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, place);
    }

    @Override
    public String toString() {
        return "Znaika{" +
                "name='" + name + '\'' +
                ", place=" + place +
                '}';
    }
}
