import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

class MyFunctionTest {

    private static MyFunction func;
    private static double eps;

    @BeforeAll
    static void init() {
        eps = 0.01;
        func = new MyFunction(eps);
    }

    Map<Double, Double> parameterAnswer = new HashMap<>();
    {
        parameterAnswer.put(-16.697, 0.0);
        parameterAnswer.put(-14.98, 0.0);
        parameterAnswer.put(-13.489, 0.829);
        parameterAnswer.put(-11.718, 2.378);
        parameterAnswer.put(-10.413, 0.0);
        parameterAnswer.put(-8.697, 0.0);
        parameterAnswer.put(-7.206, 0.829);
        parameterAnswer.put(-5.435, 2.378);
        parameterAnswer.put(-9.0, 4.296);
        parameterAnswer.put(-8.0, -7.695);
        parameterAnswer.put(0.0, Double.NaN);
        parameterAnswer.put(1.0, Double.NaN);
        parameterAnswer.put(2.0, 0.277);
        parameterAnswer.put(0.5, Double.NaN);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-16.697, -14.98,-13.489,-11.718,-10.413,-8.697,-7.206,-5.435,-9.0,-8.0,0.0,1.0,2.0,0.5})
    public void generalTest(double x) {
        Assertions.assertEquals(parameterAnswer.get(x), func.calculate(x), eps);
    }

}