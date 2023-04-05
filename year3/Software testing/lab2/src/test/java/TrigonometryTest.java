import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import trigonometric_func.CustomTrigonometry;

public class TrigonometryTest {
    private static double DELTA;
    private static CustomTrigonometry trigonometry;

    @BeforeAll
    static void init() {
        DELTA = 0.0001;
        trigonometry = new CustomTrigonometry(DELTA);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-10.0, -1.01, -1, -0.99, -0.5, 0, 10.0, 1.01, 1, 0.99, Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY})
    public void generalTest(double param) {
        Assertions.assertEquals(Math.cos(param), trigonometry.cos(param, DELTA), DELTA);
        Assertions.assertEquals((Math.cos(param)/Math.sin(param)), trigonometry.cot(param, DELTA), DELTA);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.99, -0.1})
    public void symmetricalTest(double param) {
        Assertions.assertEquals(Math.cos(param), trigonometry.cos(-param, DELTA), DELTA);
        Assertions.assertEquals(1 / Math.tan(param),  -(trigonometry.cot(-param, DELTA)), DELTA);
    }
    @Test
    public void symmetricalTestZero() {
        Assertions.assertEquals(Math.cos(0), trigonometry.cos(-0.0, DELTA), DELTA);
    }

}