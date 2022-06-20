package cls;

import utility.AbstractWeight;
import utility.Simple;

import java.util.Objects;

public class HaveWeight extends AbstractWeight implements Simple {

    private String name;
    private byte weight;

    public HaveWeight(String name, byte weight){
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
        System.out.print(", находясь под действием силы тяжести, как бы сжимаются или сплющиваются хотя бы на самую ничтожную величину.");
        System.out.println("");
    }

    @Override
    public String toString() {
        return "HaveWeight{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HaveWeight that = (HaveWeight) o;
        return weight == that.weight &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight);
    }
}
