import logarithmic_func.CustomLog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LogTest {

    private static double DELTA;
    private static CustomLog log;

    @BeforeAll
    static void init() {
        DELTA = 0.0001;
        log = new CustomLog(DELTA);
    }

    @Test
    public void forceBurst() {
        for (int x = 0; x < 10; x+=1) {
            assertion(((double)x) / 100, 2);
            assertion(((double)x) / 100, 3);
            assertion(((double)x) / 100, 5);
            assertion(((double)x) / 100, 10);
        }
    }

    public void assertion(double x, double base) {
        Assertions.assertEquals(Math.log(x)/Math.log(base), log.calculate(base, x), DELTA);
    }
}