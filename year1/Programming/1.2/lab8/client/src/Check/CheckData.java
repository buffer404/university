package Check;

import Collection.OrganizationType;
import Controller.ControllerMain;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CheckData {
    public static double CheckDouble(TextField input, Label output) {
        double x;
            try {
                x = Double.parseDouble(input.getText());
                if(x<=0){
                    x=CheckDouble(input,output);
                }
            } catch (InputMismatchException e) {
                output.setText("Введите double");
                x=CheckDouble(input,output);
            }
            catch (NoSuchElementException e){
                System.exit(0);
                return 1;
            }
        return x;
    }
    public static String CheckNull(TextField input, Label output){
        try{
            String x = ControllerMain.s;
            if(x.equals("")){
                output.setText("Не может быть пустой строкой!");
                x = CheckNull(input,output);
            }
            return x;
        }
        catch (NoSuchElementException e){
            System.exit(0);
            return "";
        }
    }
    public static String CheckNull6(TextField input, Label output){
        String x = input.getText();
        if(x.equals("") | x.length()<6){
            output.setText("Не может быть пустой строкой, должна быть больше 6!");
            x = CheckNull6(input,output);
        }
        return x;
    }
    public static int CheckInt(TextField input, Label output){
        int x = 0;
        try {
            x = Integer.parseInt(input.getText());
            if (x<=0){
               output.setText("Должен быть больше 0");
                x=CheckInt(input,output);
            }
        } catch (InputMismatchException e) {
            output.setText("Введите int, больше 0");
            x=CheckInt(input,output);
        }
        catch (NoSuchElementException e){
            System.exit(0);
        }
        return x;
    }
    public static long CheckLong(TextField input, Label output){
        long x = 0;
        try {
            x = Long.parseLong(input.getText());
            if (x<0){
                output.setText("Должен быть больше 0");
                x=CheckInt(input,output);
            }
        } catch (InputMismatchException e) {
            output.setText("Введите Long, больше 0");
            x=CheckInt(input,output);
        }
        catch (NoSuchElementException e){
            System.exit(0);
        }
        return x;
    }
    public static OrganizationType CheckType(TextField input, Label output){
        try{
            String x = input.getText();
            if (x.equals("государственная")) {
                return OrganizationType.GOVERNMENT;
            } else if (x.equals("коммерческая")) {
                return OrganizationType.COMMERCIAL;
            } else if (x.equals("частная")) {
                return OrganizationType.PRIVATE_LIMITED_COMPANY;
            } else if (x.equals("трастовая")) {
                return OrganizationType.TRUST;
            }  else {
                output.setText("Некорректный ввод");
                return CheckType(input,output);
            }
        }
        catch (NoSuchElementException e){
            System.exit(0);
        }
        return null;
    }

    public static boolean Checkbool() {
        Scanner scanner1 = new Scanner(System.in);
        boolean x=false;
        try {
            x = scanner1.nextBoolean();
            return x;
        } catch (InputMismatchException e) {
            System.out.println("Введите true или false");
            x = Checkbool();
        }
        catch (NoSuchElementException e){
            System.exit(0);
        }
        return x;
    }
    public static boolean cint(String i){
        try {
            int j = Integer.parseInt(i);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static int CheckInteger(String s) {
        int x = 0;
        try {
            x = Integer.parseInt(s);
            if (x <= 0) {
                System.out.println("Должен быть больше 0");
                return -1;
            }
        } catch (InputMismatchException e) {
            System.out.println("Введите int, больше 0");
            return -1;
        }
            catch (NumberFormatException e) {
                System.out.println("Введите int, больше 0");
                return -1;
        } catch (NoSuchElementException e) {
            System.out.println("Введите int, больше 0");
            return -1;
        }
        return x;
    }
}
