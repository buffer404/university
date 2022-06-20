package Main;

import Check.CheckData;
import Collection.Address;
import Collection.Coordinates;
import Collection.Location;
import Collection.OrganizationType;
import Command.*;
import Interface.ClearId;
import NET.Login;
import NET.Send;

import java.io.IOException;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Scanner;

public class Work {
    public static Queue<String> history = new LinkedList<>();
    public void work(Scanner scanner) {
        System.out.println("Здравствуйте, если вы уже зарегистрированы, введите true, если нет, то введите false");
        boolean k = CheckData.Checkbool();
        while (!Login.login(k)){}

        System.out.println("Введите команду");
        System.out.print(">");
        try {
            String full_command = scanner.nextLine();
            String command = full_command.trim().split(" ")[0];

            while (!command.equals("exit")) {
                history.add(command);
                if (command.equals("show")) {
                    Show show = new Show();
                    Send.send(show);
                } else if (command.equals("info")) {
                    Info info = new Info();
                    Send.send(info);
                } else if (command.equals("AverageOfAnnualTurnover")) {
                    AverageOfAnnualTurnover averageOfAnnualTurnover = new AverageOfAnnualTurnover();
                    Send.send(averageOfAnnualTurnover);
                }  else if (command.equals("history")) {
                    History h = new History(history);
                    Send.send(h);
                } else if (command.equals("clear")) {
                    Clear clear = new Clear();
                    Send.send(clear);
                } else if (command.equals("CountLessThanAnnualTurnover")) {
                    System.out.println("Введите размер зароботка");
                    CountLessThanAnnualTurnover countLessThanAnnualTurnover = new CountLessThanAnnualTurnover(CheckData.CheckDouble());
                    Send.send(countLessThanAnnualTurnover);
                } else if (command.equals("printUniqueFullName")) {
                    PrintUniqueFullName printUniqueFullName = new PrintUniqueFullName();
                    Send.send(printUniqueFullName);
                } else if (command.equals("add")) {
                    System.out.println("Введине название организации");
                    String name = CheckData.CheckNull();
                    System.out.println("Введите долготу вашей органиции (не может быть пустой строкой!)");
                    int coordinateX = CheckData.CheckInt();
                    System.out.println("Введите широту вашей органиции (должна быть больше 0)");
                    int coordinateY = CheckData.CheckInt();
                    System.out.println("Введите годовой доход вашей органиции (должен быть больше нуля!)");
                    double annualTurnover = CheckData.CheckDouble();
                    System.out.println("Введите колчесто работников в вашей организации (должно быть больше нуля)");
                    long employeesCount = CheckData.CheckLong();
                    System.out.print("Введине полное название организации");
                    String fullName = CheckData.CheckNull();
                    System.out.println("Ваша организация: государсвенная, частная, коммерческая или трастовая");
                    OrganizationType organizationType = CheckData.CheckType();
                    System.out.println("Введите почтовый индекс вашей организации (должен быть не меньше 6 символов)");
                    String zipCode = CheckData.CheckNull6();
                    System.out.println("Введите координату Х города");
                    double x = CheckData.CheckDouble();
                    System.out.println("Введите координату Y города");
                    int y = CheckData.CheckInt();
                    System.out.println("Введите координату Z города");
                    int z = CheckData.CheckInt();
                    System.out.println("Введите название города");
                    String townName = CheckData.CheckNull();
                    Add add = new Add(name, new Coordinates(coordinateX, coordinateY), annualTurnover, fullName, employeesCount, organizationType, new Address(zipCode, new Location(x, y, z, townName)));
                    Send.send(add);
                }
                else if (command.equals("update")) {
                    System.out.println("Введине id");
                    int id = CheckData.CheckInt();
                    System.out.println("Введине название организации");
                    String name = CheckData.CheckNull();
                    System.out.println("Введите долготу вашей органиции (не может быть пустой строкой!)");
                    int coordinateX = CheckData.CheckInt();
                    System.out.println("Введите широту вашей органиции (должна быть больше 0)");
                    int coordinateY = CheckData.CheckInt();
                    System.out.println("Введите годовой доход вашей органиции (должен быть больше нуля!)");
                    double annualTurnover = CheckData.CheckDouble();
                    System.out.println("Введите колчесто работников в вашей организации (должно быть больше нуля)");
                    long employeesCount = CheckData.CheckLong();
                    System.out.print("Введине полное название организации");
                    String fullName = CheckData.CheckNull();
                    System.out.println("Ваша организация: государсвенная, частная, коммерческая или трастовая");
                    OrganizationType organizationType = CheckData.CheckType();
                    System.out.println("Введите почтовый индекс вашей организации (должен быть не меньше 6 символов)");
                    String zipCode = CheckData.CheckNull6();
                    System.out.println("Введите координату Х города");
                    double x = CheckData.CheckDouble();
                    System.out.println("Введите координату Y города");
                    int y = CheckData.CheckInt();
                    System.out.println("Введите координату Z города");
                    int z = CheckData.CheckInt();
                    System.out.println("Введите название города");
                    String townName = CheckData.CheckNull();
                    Update update = new Update(id, name, new Coordinates(coordinateX, coordinateY), annualTurnover, fullName, employeesCount, organizationType, new Address(zipCode, new Location(x, y, z, townName)));
                    Send.send(update);
                }
                else if (command.equals("help")) {
                    Help help = new Help();
                    help.strat(null, null);
                }
                else if(command.equals("RemoveById") && CheckData.cint(full_command.trim().split(" ")[1])){
                    RemoveById removeById = new RemoveById(Integer.parseInt(full_command.trim().split(" ")[1]));
                    Send.send(removeById);
                }

                System.out.print(">");
                try{
                    full_command = scanner.nextLine();
                }
                catch (NoSuchElementException e){

                }
                command = full_command.trim().split(" ")[0];
            }

        } catch (Exception e) {
            System.err.println("Завершение работы");
            System.exit(0);
        }
    }
}
