package Main;

import Check.CheckData;
import NET.Init;
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException { //Сетевой канал
    Scanner scanner = new Scanner(System.in);
        {
            System.out.println("Enter server port");
            int ports = CheckData.CheckInt();
            Init init = new Init(ports);
        }
    Work work = new Work();
    work.start(scanner);
    }
}
