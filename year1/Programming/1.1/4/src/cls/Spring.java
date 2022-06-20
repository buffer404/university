package cls;

import utility.ThingAbstract;

import java.util.Objects;

public class Spring extends ThingAbstract {
    private String name;
    public Spring(String s){
        name = s;
    }
    @Override
    public String info() {
        return name;
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

    @Override
    public String toString() {
        return "Spring{" +
                "name='" + name + '\'' +
                '}';
    }
}
