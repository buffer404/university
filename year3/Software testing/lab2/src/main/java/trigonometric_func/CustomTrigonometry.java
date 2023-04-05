package trigonometric_func;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;
import java.text.DecimalFormat;

public class CustomTrigonometry {

    private Double DELTA = 0.0001;
    private final CustomSin customSin;

    public CustomTrigonometry(){customSin = new CustomSin();};
    public CustomTrigonometry(Double delta){

        DELTA = delta;
        customSin = new CustomSin();
    }
    public CustomTrigonometry(CustomSin customSin) {this.customSin = customSin;}

    public double cos(Double value){
        return customSin.calculate(Math.PI/2 + value, DELTA);
    }

    public double cos(Double value, Double delta){
        //double x = Math.PI/2 + value;
        //String result = String.format("%.2f", value).replace(',', '.');
        //return customSin.calculate(Double.valueOf(result), delta);
        //
        //
        return customSin.calculate(Math.PI/2 + value, delta);
    }

    public double sin(Double value){
        return customSin.calculate(value);
    }

    public double sin(Double value, Double delta){
        return customSin.calculate(value, delta);
    }

    public double cot(Double value){
        double x = value - Math.PI * Math.floor((value + Math.PI/2) / Math.PI);
        return (x > 0) ? - cos(-x) / sin(-x) : cos(x) / sin(x);
    }

    public double cot(Double value, Double delta){
        delta *= 0.1;
        return cos(value, delta) / customSin.calculate(value, DELTA);
    }

    public void writeCSV(double x, Writer out, double eps) {
        double curCos = cos(x, eps);
        double curCot = cot(x, eps);
        try {
            CSVPrinter printer = CSVFormat.DEFAULT.print(out);
            printer.printRecord(x, curCos, curCot);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setDELTA(Double DELTA) {
        this.DELTA = DELTA;
    }

}