package Except;

import start.Init;

import java.util.Scanner;

public class Exception {
    public static void err (){
        Scanner scanner = new Scanner(System.in);
        Init init = new Init(scanner);
        init.init_go();
    }
}
