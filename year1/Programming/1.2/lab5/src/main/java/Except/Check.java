package Except;

import java.util.*;

/**
 * Класс, который используется при проверки корректности ввода данных
 */
public class Check {
    static Scanner scanner = new Scanner(System.in);
    static Deque<String> script = new ArrayDeque<>();
    
    public static boolean CheckStringNull (String s){
        if (s.equals("") | s.trim().equals("") ){
            System.err.println("Некорректный ввод данных");
            return true;
        }
        return false;
    }

    public static boolean CheckIntZero (int s){
        if (s==0){
            System.err.println("Некорректный ввод данных");
            return true;
        }
        return false;
    }

    public static boolean CheckMS(int n){
        if (n<=-70){
            System.err.println("Некорректный ввод данных");
            return true;
        }
        return false;
    }

    public static boolean CheckMZero(double n){
        if (n<=0){
            System.err.println("Некорректный ввод данных");
            return true;
        }
        return false;
    }

    public static boolean Check915 (String n){
        if (n.length()>915){
            System.err.println("Некорректный ввод данных");
            return true;
        }
        return false;
    }

    public static boolean CheckLong (Long n){
        if (n<=0){
            System.err.println("Некорректный ввод данных");
            return true;
        }
        return false;
    }

    public static boolean CheckString6 (String n){
        if (n.length()<6){
            System.err.println("Некорректный ввод данных");
            return true;
        }
        return false;
    }

    public static boolean CheckScript(String s){
        if (script.contains(s)){
            script.clear();
            return false;
        }
        script.addFirst(s);
        return true;
    }

    public static void CheckDellScript(){
        script.removeFirst();
    }
}
