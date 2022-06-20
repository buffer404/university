package cls;

import utility.Thing;

import java.util.Objects;

public class MoonRoad implements Thing {
    private String name = "лунной дорожке";

    @Override
    public String info() {
        return name;
    }

    @Override
    public String toString() {
        return "MoonRoad{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoonRoad moonRoad = (MoonRoad) o;
        return Objects.equals(name, moonRoad.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
