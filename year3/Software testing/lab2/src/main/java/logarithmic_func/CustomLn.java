package logarithmic_func;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;

public class CustomLn {

    private static Double DELTA = 0.0001;

    public CustomLn(){}
    public CustomLn(Double delta){
        DELTA = delta;
    }

    public double calculate(Double x){
        return calculate(x, DELTA);
    }
    public Double calculate(Double x, Double delta){
        if (x.isNaN() || delta.isNaN()) return Double.NaN;
        if (x == 0) return Double.NEGATIVE_INFINITY;
        if (x < 0) return Double.NaN;
        delta*=0.01;

        double newX = delta;
        double result = 0;

        if (x <= 2){
            x -= 1;
            result = x;
            int iter = 2;
            while (Math.abs(newX) >= delta){
                newX = Math.pow(x, iter) / iter;
                result = (iter % 2 == 0) ? (result -= newX) : (result += newX);
                iter+=1;
            }
            return result;
        } else{
            x = (x-1) / (x+1);
            int iter = 1;
            while (Math.abs(newX) >= delta){
                newX = Math.pow(x, iter) / iter;
                result += newX;
                iter += 2;
            }
            return result*2;
        }
    }

    public double writeCSV(double x, Writer out, double eps) {
        double res = calculate(x, eps);
        try {
            CSVPrinter printer = CSVFormat.DEFAULT.print(out);
            printer.printRecord(x, res);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

}
