package cls;

import utility.AbstractPlace;
import utility.Simple;

import java.util.Objects;

public class  Znaika extends AbstractPlace implements Simple {
    private String name;
    private String place;

    public Znaika(String name, String place){
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
        System.out.println(place);
    }

    @Override
    public void setPlace(String place) {
        this.place=place;
    }

    public void notice(String s){
        getName();
        System.out.print(", заметил "+s);
    }

    public void pull(String s){
        System.out.print(" потянул за "+s);
    }

    public void go(String s){
        System.out.print(", и зашагал по "+s);
        System.out.println("");
    }

    @Override
    public String toString() {
        return "Znaika{" +
                "name='" + name + '\'' +
                ", place='" + place + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Znaika znaika = (Znaika) o;
        return Objects.equals(name, znaika.name) &&
                Objects.equals(place, znaika.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, place);
    }
}
