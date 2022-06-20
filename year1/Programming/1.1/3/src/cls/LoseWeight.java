package cls;

import utility.AbstractWeight;
import utility.Simple;

import java.util.Objects;

public class LoseWeight extends AbstractWeight implements Simple {

    private String name;
    private byte weight;

    public LoseWeight(String name, byte weight){
        this.name=name;
        this.weight=weight;
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
    public void getWeight() {
        System.out.println(weight);
    }

    @Override
    public void setWeight(byte weight) {
        this.weight=weight;
    }

    @Override
    public void Info(String s){
        getName();
        System.out.println(", обычно поднимаются вверх (если они, конечно, не закреплены). Они разжимаются, выпрямляются, в результате чего отталкиваются, как "+s+", от поверхности, на которой до этого неподвижно стояли");
    }

    @Override
    public String toString() {
        return "LoseWeight{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoseWeight that = (LoseWeight) o;
        return weight == that.weight &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight);
    }
}
