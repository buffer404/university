package cls;

import utility.Thing;

import java.util.Objects;

public class Cord implements Thing {
    private String name = "шнур";

    @Override
    public String info() {
        return name;
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
