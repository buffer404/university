package Command;

import Except.IncorrectInput;
import Org.*;

import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * Класс, используемый для обновления информации о организации, по её id
 */
public class Update {
    /**
     * @param id ID организации
     * @param a Коллекция организаций
     * @param scanner Класс, который определяет как мы будем считывать информацию
     */

    private final int id;
    private final ArrayDeque<Organization> array;
    private final Scanner scanner;

    public Update(int id, ArrayDeque<Organization> a, Scanner scanner) {
        this.id = id;
        array = a;
        this.scanner = scanner;
    }

    public void Make_update() {
        System.out.println("Введите название вашей организации");
        String name = scanner.nextLine();


        System.out.println("Введите широту вашей органиции(она должна быть больше, чем -70)");
        int coordinateX = Integer.parseInt(scanner.nextLine());

        System.out.println("Введите широту вашей органиции");
        int coordinateY = Integer.parseInt(scanner.nextLine());

        System.out.println("Введите годовой доход вашей органиции");
        double annualTurnover = Double.parseDouble(scanner.nextLine());

        System.out.println("Введите полное название вашей организации");
        String fullName = scanner.nextLine();
        for (Organization o : array) {
            if (o.getFullName().equals(fullName)) {
                new IncorrectInput();
            }
        }

        System.out.println("Введите колчесто работников в вашей организации");
        long employeesCount = Long.parseLong(scanner.nextLine());

        System.out.println("Ваша организация: государсвенная, частная, коммерческая или трастовая");
        OrganizationType organizationType = null;
        String StringOrganizationType = scanner.nextLine();
        if (StringOrganizationType.equals("государственная")) {
            organizationType = OrganizationType.GOVERNMENT;
        } else if (StringOrganizationType.equals("коммерческая")) {
            organizationType = OrganizationType.COMMERCIAL;
        } else if (StringOrganizationType.equals("частная")) {
            organizationType = OrganizationType.PRIVATE_LIMITED_COMPANY;
        } else if (StringOrganizationType.equals("трастовая")) {
            organizationType = OrganizationType.TRUST;
        } else {
            new IncorrectInput();
        }

        System.out.println("Введите почтовый индекс вашей организации (должен быть не меньше 6)");
        String zipCode = scanner.nextLine();

        System.out.println("Теперь нам понадобиться информация о городе, где расположениа ваша организация");
        System.out.println("Введите координату Х города");
        double x = Double.parseDouble(scanner.nextLine());
        System.out.println("Введите координату Y города");
        int y = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите координату Z города");
        Integer z = Integer.valueOf(scanner.nextLine());
        System.out.println("Введите название города");
        String TownName = scanner.nextLine();

        Coordinates coordinates = new Coordinates(coordinateX, coordinateY);
        Location town = new Location(x, y, z, TownName);
        Address postalAddress = new Address(zipCode, town);

        Organization organization = new Organization(id, name, coordinates, annualTurnover, fullName, employeesCount, organizationType, postalAddress);
        array.add(organization);
        System.out.println("Организация успешно обновлена!");
    }

}
