package task1;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.junit.Assert;

import java.io.FilterOutputStream;

@RunWith(DataProviderRunner.class)
public class ArcCosFunctionTest {
    double inaccuracy = 0.1;

    @DataProvider
    public static Object[] dataCorrect(){
        return new Object[]{-1.0, -0.8, -0.5, -0.1, 0.0, 0.1, 0.5, 0.7, 1.0};
    }

    @DataProvider
    public static Object[] dataIncorrect(){
        return new Object[]{-1.1, 1.1, Double.NaN, null, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY};
    }

    @Test
    @UseDataProvider("dataCorrect")
    public void testCorrect(Double x){
        Assert.assertTrue(Math.abs(ArcCosFunction.arcCos(x) - Math.acos(x)) <= inaccuracy);
    }

    @Test
    @UseDataProvider("dataIncorrect")
    public void testIncorrect(Double x){
        Assert.assertTrue(Double.isNaN(ArcCosFunction.arcCos(x)));
    }

    @Test
    public void checkSymmetry(){
        Double x = 0.5;
        System.out.println(ArcCosFunction.arcCos(0.0) - ArcCosFunction.arcCos(x));
        System.out.println(ArcCosFunction.arcCos(x) - ArcCosFunction.arcCos(0.0));
        Assert.assertTrue(ArcCosFunction.arcCos(0.0) - ArcCosFunction.arcCos(x)  == ArcCosFunction.arcCos(-x) - ArcCosFunction.arcCos(0.0));
    }

}
