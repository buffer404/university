package start;

import Org.Organization;
import utility.Pharse;

import java.util.ArrayDeque;
import java.util.Date;
import java.util.Queue;
import java.util.Scanner;

/**
 * Промежуточный класс, который решает, что комманды пользователя будут приниматься программой или с помощью коммандной строки или из файла
 */
public class Init {
    ArrayDeque<Organization> ArrayOrganizations = Initialization.ArrayOrganizations;
    Pharse pharse = Initialization.pharse;
    Queue<String> ArrayCommand = Initialization.ArrayCommand;
    Date DateInitialization = Initialization.DateInitialization;
    Scanner scanner;

    /**
     * Начало работы с клиентом
     * @param scanner Класс, который определяет как мы будем считывать информацию
     */
    public Init(Scanner scanner){
        this.scanner=scanner;
    }

    public void init_go(){
        Run run = new Run(ArrayOrganizations, pharse, ArrayCommand, DateInitialization, scanner);
    }
}
