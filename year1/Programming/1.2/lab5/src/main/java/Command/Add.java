package Command;

import Except.Check;
import Except.IncorrectInput;
import Org.*;

import java.util.ArrayDeque;
import java.util.Scanner;

import static Except.Check.*;

/**
 * Публичный класс add, который используется при создании новой организации
 */
public class Add {

    private final ArrayDeque<Organization> array;
    private final Scanner scanner;
    private String name;
    private int coordinateX;
    private int coordinateY;
    private double annualTurnover;
    private long employeesCount;
    private String fullName;
    private OrganizationType organizationType;
    private String zipCode;
    private double x;
    private int y;
    private Integer z;
    private boolean flag = true;



    public Add(ArrayDeque<Organization> a, Scanner scanner) {
        array = a;
        this.scanner = scanner;
    }

    public void add_new_organization() {
/**
 * Название организации
 */
        while (flag){
            System.out.println("Введите название вашей организации, (не может быть пустой строкой!)");
            name = scanner.nextLine();
            flag = CheckStringNull(name);
        }
        flag=true;

        /**
         * Географическая широта местоположения организации
         */
        while (flag){
            System.out.println("Введите широту вашей органиции (она должна быть больше, чем -70)");
            try {
                coordinateX = Integer.parseInt(scanner.nextLine());
                flag = CheckMS(coordinateX);
            }
            catch (NumberFormatException ignored){
                flag = true;
                System.err.println("Некорректный ввод данных");
            }
        }
        flag = true;
        /**
         * Географическая долгота местоположения организации
         */
        while(flag){
            System.out.println("Введите долготу вашей органиции (не может быть пустой строкой!)");
            try {
                coordinateY = Integer.parseInt(scanner.nextLine());
                flag = CheckIntZero(coordinateY);
            } catch (NumberFormatException ignored) {
                flag = true;
                System.err.println("Некорректный ввод данных");
            }
        }

        flag=true;
        /**
         * Годовой доход оргинизации
         */
        while(flag){
            System.out.println("Введите годовой доход вашей органиции (должен быть больше нуля!)");
            try {
                annualTurnover = Double.parseDouble(scanner.nextLine());
                flag = CheckMZero(annualTurnover);
            } catch (NumberFormatException ignored){
                flag = true;
                System.err.println("Некорректный ввод данных");
            }
        }
        flag=true;
        /**
         * Полное название организации
         */
        while (flag){
            System.out.println("Введите полное название вашей организации (Длина строки не должна быть больше 915)");
            fullName = scanner.nextLine();

            for (Organization o1 : array) {
                if (o1.getFullName().equals(fullName)) {
                    System.err.println("Организация с данным названием уже существует!");
                    flag = false;
                }
            }
            if (flag){
                flag = Check915(fullName);
            }
            else{
                flag = true;
            }
        }
        flag = true;
        /**
         * Количество работников
         */
        while (flag){
            System.out.println("Введите колчесто работников в вашей организации (должно быть больше нуля)");
            try {
                employeesCount = Long.parseLong(scanner.nextLine());
                flag = CheckLong(employeesCount);
            } catch (NumberFormatException ignored) {
                flag = true;
                System.err.println("Некорректный ввод данных");
            }
        }
        flag = true;

        /**
         * Тип организации
         */
        while (flag){
            System.out.println("Ваша организация: государсвенная, частная, коммерческая или трастовая");
            String StringOrganizationType = scanner.nextLine();
            if (StringOrganizationType.equals("государственная")) {
                organizationType = OrganizationType.GOVERNMENT;
                flag=false;
            } else if (StringOrganizationType.equals("коммерческая")) {
                organizationType = OrganizationType.COMMERCIAL;
                flag = false;
            } else if (StringOrganizationType.equals("частная")) {
                organizationType = OrganizationType.PRIVATE_LIMITED_COMPANY;
                flag = false;
            } else if (StringOrganizationType.equals("трастовая")) {
                organizationType = OrganizationType.TRUST;
                flag = false;
            }  else {
                System.err.println("Вы ввели неправильный тип организации попробуйте снова!");
                flag = true;
            }
        }
        flag = true;

        /**
         * Почтовый индекс организации
         */
        while (flag){
            System.out.println("Введите почтовый индекс вашей организации (должен быть не меньше 6 символов)");
            try {
                zipCode = scanner.nextLine();
                flag = Check.CheckString6(zipCode);
            } catch (Exception ignored) {
                flag = true;
            }

        }
        flag = true;

        System.out.println("Теперь нам понадобиться информация о городе, где расположениа ваша организация");
        /**
         *Географицеская координата X города, где находится главный офис организации
         */
        while (flag){
            System.out.println("Введите координату Х города (не может быть пустой строкой!)");
            try {
                x = Double.parseDouble(scanner.nextLine());
                flag = false;
            } catch (NumberFormatException ignored) {
                flag = true;
                System.err.println("Некорректный ввод данных");
            }
        }
        flag = true;
        /**
         *Географицеская координата Y города, где находится главный офис организации
         */
        while (flag){
            System.out.println("Введите координату Y города");
            try {
                y = Integer.parseInt(scanner.nextLine());
                flag = false;
            } catch (NumberFormatException ignored) {
                flag = true;
                System.err.println("Некорректный ввод данных");
            }
        }
        flag = true;
        /**
         *Географицеская координата Z города, где находится главный офис организации
         */
        while (flag){
            System.out.println("Введите координату Z города (не может быть пустой строкой!)");
            try {
                z = Integer.valueOf(scanner.nextLine());
                flag = false;
            } catch (NumberFormatException ignored) {
                flag = true;
                System.err.println("Некорректный ввод данных");
            }
        }

        /**
         * Название города, где находится главный офис организации
         */
        System.out.println("Введите название города");
        String TownName = scanner.nextLine();

        Coordinates coordinates = new Coordinates(coordinateX, coordinateY);
        Location town = new Location(x, y, z, TownName);
        Address postalAddress = new Address(zipCode, town);

        Organization organization = new Organization(name, coordinates, annualTurnover, fullName, employeesCount, organizationType, postalAddress);
        array.add(organization);
        System.out.println("Организация " + name + " успешно добавлена!");
    }
}
