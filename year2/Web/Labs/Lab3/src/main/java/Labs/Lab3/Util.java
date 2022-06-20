package Labs.Lab3;

public class Util {
    public static Integer getCountsOfDigits(Double number) {
        System.out.println(number.toString());
        return number.toString().length();
    }
    public static Double cutDouble(Double num){
        return Double.parseDouble(num.toString().substring(0, 7));
    }
}
