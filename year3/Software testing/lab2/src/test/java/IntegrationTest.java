import logarithmic_func.CustomLn;
import logarithmic_func.CustomLog;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import trigonometric_func.CustomSin;
import org.mockito.Mockito;
import trigonometric_func.CustomTrigonometry;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class IntegrationTest {

    private static double DELTA = 0.01;

    static CustomSin sinMock;
    static CustomLn lnMock;
    static CustomLog logMock;
    static CustomTrigonometry trigonometryMock;

    static Reader sinIn;
    static Reader trigonometryIn;
    static Reader logIn;
    static Reader lnIn;

    @BeforeAll
    static void init() {
        sinMock = Mockito.mock(CustomSin.class);
        lnMock = Mockito.mock(CustomLn.class);
        logMock = Mockito.mock(CustomLog.class);
        trigonometryMock = Mockito.mock(CustomTrigonometry.class);

        try {
            sinIn = new FileReader("src/main/resources/SinOut.csv");
            trigonometryIn = new FileReader("src/main/resources/TrigonometryOut.csv");
            logIn = new FileReader("src/main/resources/LogOut.csv");
            lnIn = new FileReader("src/main/resources/LnOut.csv");

            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(sinIn);
            for (CSVRecord record : records) {
                double x = Double.parseDouble(record.get(0));
                double x3 = -Math.abs(x - Math.PI * Math.floor((x + Math.PI/2) / Math.PI));
                Mockito.when(sinMock.calculate(x)).thenReturn(Double.parseDouble(record.get(1)));
                Mockito.when(sinMock.calculate(x, 0.0001)).thenReturn(Double.parseDouble(record.get(1)));
                Mockito.when(sinMock.calculate(Math.PI/2 + Double.parseDouble(record.get(0)))).thenReturn(Double.parseDouble(record.get(2)));
                Mockito.when(sinMock.calculate(x3)).thenReturn(Double.parseDouble(record.get(3)));
                Mockito.when(sinMock.calculate(x3 + Math.PI/2)).thenReturn(Double.parseDouble(record.get(4)));
                Mockito.when(sinMock.calculate(Math.PI/2 + Double.parseDouble(record.get(0)), 0.0001)).thenReturn(Double.parseDouble(record.get(2)));
                Mockito.when(sinMock.calculate(x3, 0.0001)).thenReturn(Double.parseDouble(record.get(3)));
                Mockito.when(sinMock.calculate(x3 + Math.PI/2, 0.0001)).thenReturn(Double.parseDouble(record.get(4)));
                Mockito.when(trigonometryMock.sin(Double.parseDouble(record.get(0)))).thenReturn(Double.parseDouble(record.get(1)));
                Mockito.when(trigonometryMock.sin(Double.parseDouble(record.get(0)), 0.0001)).thenReturn(Double.parseDouble(record.get(1)));
            }

            records = CSVFormat.DEFAULT.parse(trigonometryIn);
            for (CSVRecord record : records) {
                Mockito.when(trigonometryMock.cos(Double.parseDouble(record.get(0)))).thenReturn(Double.parseDouble(record.get(1)));
                Mockito.when(trigonometryMock.cot(Double.parseDouble(record.get(0)))).thenReturn(Double.parseDouble(record.get(2)));
            }

            records = CSVFormat.DEFAULT.parse(logIn);
            for (CSVRecord record : records) {
                Mockito.when(logMock.calculate(2.0, Double.parseDouble(record.get(0)))).thenReturn(Double.parseDouble(record.get(1)));
                Mockito.when(logMock.calculate(3.0, Double.parseDouble(record.get(0)))).thenReturn(Double.parseDouble(record.get(2)));
                Mockito.when(logMock.calculate(5.0, Double.parseDouble(record.get(0)))).thenReturn(Double.parseDouble(record.get(3)));
                Mockito.when(logMock.calculate(10.0, Double.parseDouble(record.get(0)))).thenReturn(Double.parseDouble(record.get(4)));
            }
            records = CSVFormat.DEFAULT.parse(lnIn);
            for (CSVRecord record :  records) {
                Mockito.when(lnMock.calculate(Double.parseDouble(record.get(0)), 0.0001)).thenReturn(Double.parseDouble(record.get(1)));
                Mockito.when(lnMock.calculate(Double.parseDouble(record.get(0)))).thenReturn(Double.parseDouble(record.get(1)));
            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "Out.csv")
    public void testFunctionWithMocks(double x, Double f) {
        MyFunction function = new MyFunction(trigonometryMock, logMock);
        Double res = function.calculate(x);
        Assertions.assertTrue((res.isNaN() && f.isNaN()) || Math.abs(f / res - 1) < DELTA);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "Out.csv")
    public void testFunctionWithLn(double x, Double f) {
        MyFunction function = new MyFunction(trigonometryMock, new CustomLog(lnMock));
        Double res = function.calculate(x);
        Assertions.assertTrue((res.isNaN() && f.isNaN()) || Math.abs(f / res - 1) < DELTA);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "Out.csv")
    public void testFunctionWithSin(double x, Double f) {
        MyFunction function = new MyFunction(new CustomTrigonometry(sinMock), logMock);
        Double res = function.calculate(x);
        Assertions.assertTrue((res.isNaN() && f.isNaN()) || Math.abs(f / res - 1) < DELTA);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "Out.csv")
    public void testFunctionWithSinAndLn(double x, Double f) {
        MyFunction function = new MyFunction(new CustomTrigonometry(sinMock), new CustomLog(lnMock));
        Double res = function.calculate(x);
        Assertions.assertTrue((res.isNaN() && f.isNaN()) || Math.abs(f / res - 1) < DELTA);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "Out.csv")
    public void testFunctionWithLn2(double x, Double f) {
        MyFunction function = new MyFunction(new CustomTrigonometry(new CustomSin()), new CustomLog(lnMock));
        Double res = function.calculate(x);
        Assertions.assertTrue((res.isNaN() && f.isNaN()) || Math.abs(f / res - 1) < DELTA);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "Out.csv")
    public void testFunctionWithLn3(double x, Double f) {
        MyFunction function = new MyFunction(new CustomTrigonometry(new CustomSin()), new CustomLog(new CustomLn()));
        Double res = function.calculate(x);
        Assertions.assertTrue((res.isNaN() && f.isNaN()) || Math.abs(f / res - 1) < DELTA);
    }
}
