package cls;
import utility.Thing;

import java.util.Objects;

public class Spring implements Thing {
    private String name = "пружина";


    @Override
    public String info() {
        return name;
    }

    @Override
    public String toString() {
        return "Spring{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spring spring = (Spring) o;
        return Objects.equals(name, spring.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
