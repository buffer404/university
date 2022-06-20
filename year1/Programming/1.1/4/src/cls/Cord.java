package cls;

import Except.NameException;
import utility.ThingAbstract;

import java.util.Objects;

public class Cord extends ThingAbstract {
    private String name;
    public Cord(String s){
        name=s;
    }
    @Override
    public String info() throws NameException {
        if (name!=null){
            return name;
        }
        else {
            throw new NameException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cord cord = (Cord) o;
        return Objects.equals(name, cord.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Cord{" +
                "name='" + name + '\'' +
                '}';
    }
}
