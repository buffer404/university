import logarithmic_func.CustomLog;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import trigonometric_func.CustomTrigonometry;

import java.io.IOException;
import java.io.Writer;

public class MyFunction {

    private final Double CORRECTION = 0.1;
    public MyFunction(Double DELTA){
        DELTA*=CORRECTION;
        CUSTOM_TRIGONOMETRY = new CustomTrigonometry(DELTA);
        CUSTOM_LOG = new CustomLog(DELTA);

    }

    public MyFunction(CustomTrigonometry customTrigonometry, CustomLog customLog) {
        this.CUSTOM_TRIGONOMETRY = customTrigonometry;
        this.CUSTOM_LOG = customLog;
    }

    private final CustomTrigonometry CUSTOM_TRIGONOMETRY;
    private final CustomLog CUSTOM_LOG;
    public double calculate(Double value){
        if (value <= 0){
            double test0 = CUSTOM_TRIGONOMETRY.cot(value);
            double test1 = Math.pow(CUSTOM_TRIGONOMETRY.cot(value), 3);
            double test2 = CUSTOM_TRIGONOMETRY.sin(value) + CUSTOM_TRIGONOMETRY.cos(value);
            double x1 = (Math.pow(CUSTOM_TRIGONOMETRY.cot(value), 3) + (CUSTOM_TRIGONOMETRY.sin(value) + CUSTOM_TRIGONOMETRY.cos(value)));
            return x1 /  CUSTOM_TRIGONOMETRY.cot(value);
        } else{
            if (value == 0.5){
                return Double.NaN;
            }
            double x1 =  Math.pow(CUSTOM_LOG.calculate(3.0, value) - CUSTOM_LOG.calculate(10.0, value), 2) + (CUSTOM_LOG.calculate(5.0, value) - CUSTOM_LOG.calculate(10.0, value));
            double x2 = x1 / (CUSTOM_LOG.calculate(2.0, value) + 1);
            return x2 / CUSTOM_LOG.calculate(5.0, value);
        }
    }

    public double writeCSV(double x, Writer out, double eps) {

        double res = calculate(x);
        try {
            CSVPrinter printer = CSVFormat.DEFAULT.print(out);
            printer.printRecord(x, res);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

}
