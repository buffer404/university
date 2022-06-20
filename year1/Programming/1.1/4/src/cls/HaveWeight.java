package cls;

import Except.NameException;
import utility.AbstractWeight;
import utility.ThingAbstract;
import utility.Weight;

import java.util.Objects;


public class HaveWeight extends AbstractWeight {

    Weight weight;

    public HaveWeight(Weight weight) {
        this.weight=weight;
    }

    @Override
    public void info() throws NameException {
        class Info{
            public String AboutWeight() throws NameException {
                if (weight==Weight.HAVEWEIGHT){
                    ThingAbstract thingA = new ThingAbstract(){
                        @Override
                        public String info() {
                            return "Предмет";
                        }
                    };
                    return(thingA.info()+", находясь под действием силы тяжести, как бы сжимается или сплющивается хотя бы на самую ничтожную величину");
                }
                else if (weight==Weight.NOWEIGHT){
                    return("Предмет без массы");
                }
                else{
                    String s = new Spring("пружина").info();
                    return ("Предметы, теряя вес, обычно поднимаются вверх (если они, конечно, не закреплены). Они разжимаются, выпрямляются, в результате чего отталкиваются, как "+s+", от поверхности, на которой до этого неподвижно стоял.");
                }
            }
        }
        Info i = new Info();
        System.out.println(i.AboutWeight());
    }

    @Override
    public String getWeight() {
        return weight.getAboutWeight();
    }

    @Override
    public void setWeight(Weight weight) {
        this.weight=weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HaveWeight that = (HaveWeight) o;
        return weight == that.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight);
    }

    @Override
    public String toString() {
        return "HaveWeight{" +
                "weight=" + weight +
                '}';
    }
}
