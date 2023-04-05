package logarithmic_func;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;

public class CustomLog {

    private Double DELTA = 0.0001;
    private final CustomLn customLn;

    public CustomLog(){customLn = new CustomLn();};
    public CustomLog(Double delta){
        DELTA = delta * 0.1;
        customLn = new CustomLn();
    }
    public CustomLog(CustomLn customLn) {
        this.customLn = customLn;
    }

    public void setDELTA(Double DELTA) {
        this.DELTA = DELTA;
    }

    public Double calculate(Double base, Double indicator){
        Double x = customLn.calculate(indicator, DELTA);
        Double y = customLn.calculate(base, DELTA);
        return x/y;
    }

    public void writeCSV(double x, Writer out, double eps) {
        setDELTA(eps);
        double res2 = calculate(2.0, x);
        double res3 = calculate(3.0, x);
        double res5 = calculate(5.0, x);
        double res10 = calculate(10.0, x);
        try {
            CSVPrinter printer = CSVFormat.DEFAULT.print(out);
            printer.printRecord(x, res2, res3, res5, res10);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
