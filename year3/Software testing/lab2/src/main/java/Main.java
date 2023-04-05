import logarithmic_func.CustomLn;
import logarithmic_func.CustomLog;
import trigonometric_func.CustomSin;
import trigonometric_func.CustomTrigonometry;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        CustomLn customLn = new CustomLn(0.01);
        CustomLog customLog = new CustomLog(0.01);
        CustomTrigonometry customTrigonometry = new CustomTrigonometry(0.01);
        CustomSin customSin = new CustomSin(0.001);
        MyFunction myFunction = new MyFunction(0.01);

        System.out.println(customTrigonometry.cos(-4.0));



        String inPath = "src/main/resources/In.csv";
        String outPath = "src/main/resources/Out.csv";
        File f = new File(inPath);


//        try(Scanner scanner = new Scanner(f); PrintWriter writer = new PrintWriter(outPath)){
//            scanner.useDelimiter(",");
//            int start = scanner.nextInt();
//            int end = scanner.nextInt();
//            int step = scanner.nextInt();
//            double eps = Double.parseDouble(scanner.next());
//            for (int x = start; x <= end; x++) {
//                double res = myFunction.calculate((double) x/10);
//                myFunction.writeCSV(((double) x) / 100, writer,eps);
//            }
//        } catch (Exception e){
//
//        }

        /*
        outPath = "src/main/resources/LnOut.csv";
        try(Scanner scanner = new Scanner(f); PrintWriter writer = new PrintWriter(outPath)){
            scanner.useDelimiter(",");
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            int step = scanner.nextInt();
            double eps = Double.parseDouble(scanner.next());
            for (int x = start; x <= end; x++) {
                //double res = myFunction.calculate((double) x/10);
                customLn.writeCSV((double) x / 100, writer,eps);
            }
        } catch (Exception e){

        }

         */


        /*

        outPath = "src/main/resources/LogOut.csv";
        try(Scanner scanner = new Scanner(f); PrintWriter writer = new PrintWriter(outPath)){
            scanner.useDelimiter(",");
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            int step = scanner.nextInt();
            double eps = Double.parseDouble(scanner.next());
            for (int x = start; x <= end; x++) {
                //double res = myFunction.calculate((double) x/10);
                customLog.writeCSV((double) x / 100, writer,eps);
            }
        } catch (Exception e){

        }

         */


//
//        outPath = "src/main/resources/SinOut.csv";
//        try(Scanner scanner = new Scanner(f); PrintWriter writer = new PrintWriter(outPath)){
//            scanner.useDelimiter(",");
//            int start = scanner.nextInt();
//            int end = scanner.nextInt();
//            int step = scanner.nextInt();
//            double eps = Double.parseDouble(scanner.next());
//            for (int x = start; x <= end; x++) {
//                customSin.writeCSV((double) x / 100, writer,eps);
//            }
//        } catch (Exception e){
//
//        }
//
//
//













//
//        outPath = "src/main/resources/TrigonometryOut.csv";
//        try(Scanner scanner = new Scanner(f); PrintWriter writer = new PrintWriter(outPath)){
//            scanner.useDelimiter(",");
//            int start = scanner.nextInt();
//            int end = scanner.nextInt();
//            int step = scanner.nextInt();
//            double eps = Double.parseDouble(scanner.next());
//            for (int x = start; x <= end; x++) {
//                //double res = myFunction.calculate((double) x/10);
//                customTrigonometry.writeCSV((double) x / 100, writer,eps);
//            }
//        } catch (Exception e){
//
//        }
//








    }
}