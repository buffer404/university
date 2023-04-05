package trigonometric_func;

import java.io.IOException;
import java.io.Writer;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class CustomSin {

    private static Double DELTA = 0.00001;
    private static CustomTrigonometry customTrigonometry= new CustomTrigonometry();

    public CustomSin(){};
    public CustomSin(Double delta){
        DELTA = delta;
    }

    public double calculate(Double x){
        return calculate(x, DELTA);
    }
    public Double calculate(Double x, Double delta){

        if (x == Double.NEGATIVE_INFINITY || x == Double.POSITIVE_INFINITY || Double.isNaN(x)) {
            return Double.NaN;
        } else if (x > 0) {
            while (x > Math.PI) {
                x -= 2 * Math.PI;
            }
        } else if (x < 0) {
            while (x < -Math.PI) {
                x += 2 * Math.PI;
            }
        }

        double res = x, term = x;
        double divisible = x * x * x;
        long divider = 6, sign = -1, pow = 3;

        while (Math.abs(term + sign * divisible / divider) > delta) {
            term = sign * (divisible / divider);
            res += term;

            sign *= -1;
            divisible *= x * x;
            divider = divider * ++pow * ++pow;
        }

        return res;
    }

    public double writeCSV(double x, Writer out, double eps) {
        double x2 = Math.PI/2 + x;
        double x3 = -Math.abs(x - Math.PI * Math.floor((x + Math.PI/2) / Math.PI));
        double sin = calculate(x, eps);
        double cos = calculate(x2, eps);
        double cot = calculate(x3, eps);
        double cot2 = calculate(x3 + Math.PI/2, eps);
        try {
            CSVPrinter printer = CSVFormat.DEFAULT.print(out);
            printer.printRecord(x, sin, cos, cot, cot2);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return sin;
    }

}
