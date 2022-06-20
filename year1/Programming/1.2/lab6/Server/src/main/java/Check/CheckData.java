package Check;

import Collection.OrganizationType;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CheckData {
    public static double CheckDouble() {
        Scanner scanner1 = new Scanner(System.in);
        double x;
            try {
                x = scanner1.nextDouble();
                if(x<=0){
                    x=CheckDouble();
                }
            } catch (InputMismatchException e) {
                System.out.println("Введите double");
                x=CheckDouble();
            }
        return x;
    }
    public static String CheckNull(){
        Scanner scanner1 = new Scanner(System.in);
        String x = scanner1.nextLine();
        if(x.equals("")){
            System.out.println("Не может быть пустой строкой!");
            x = CheckNull();
        }
        return x;
    }
    public static String CheckNull6(){
        Scanner scanner1 = new Scanner(System.in);
        String x = scanner1.nextLine();
        if(x.equals("") | x.length()<6){
            System.out.println("Не может быть пустой строкой, должна быть больше 6!");
            x = CheckNull();
        }
        return x;
    }
    public static int CheckInt(){
        Scanner scanner1 = new Scanner(System.in);
        int x;
        try {
            x = scanner1.nextInt();
            if (x<0){
                System.out.println("Должен быть больше 0");
                x=CheckInt();
            }
        } catch (InputMismatchException e) {
            System.out.println("Введите int, больше 0");
            x=CheckInt();
        }
        return x;
    }
    public static long CheckLong(){
        Scanner scanner1 = new Scanner(System.in);
        long x;
        try {
            x = scanner1.nextLong();
            if (x<0){
                System.out.println("Должен быть больше 0");
                x=CheckInt();
            }
        } catch (InputMismatchException e) {
            System.out.println("Введите Long, больше 0");
            x=CheckInt();
        }
        return x;
    }
    public static OrganizationType CheckType(){
        Scanner scanner1 = new Scanner(System.in);
        String x = scanner1.nextLine();
        if (x.equals("государственная")) {
            return OrganizationType.GOVERNMENT;
        } else if (x.equals("коммерческая")) {
            return OrganizationType.COMMERCIAL;
        } else if (x.equals("частная")) {
            return OrganizationType.PRIVATE_LIMITED_COMPANY;
        } else if (x.equals("трастовая")) {
            return OrganizationType.TRUST;
        }  else {
            return CheckType();
        }
    }
}
