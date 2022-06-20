package Command;

import Collection.Organization;
import Interface.Command;
import NET.Send;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayDeque;

public class Help implements Serializable, Command {
    String s;
    @Override
    public void strat(ArrayDeque<Organization> collection) throws IOException, ClassNotFoundException {
        s+=("help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "add {element} : добавить новый элемент в коллекцию\n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "removeByIdd id : удалить элемент из коллекции по его id\n" +
                "clear : очистить коллекцию\n" +
                "executeScript file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "removeFirst : удалить первый элемент из коллекции\n" +
                "removeLower {element} : удалить из коллекции все элементы, меньшие, чем заданный\n" +
                "history : вывести последние комманды (без их аргументов)\n" +
                "averageOfAnnual_turnover : вывести среднее значение поля annualTurnover для всех элементов коллекции\n" +
                "countLessThanAnnualTurnover annualTurnover : вывести количество элементов, значение поля annualTurnover которых меньше заданного\n" +
                "printUniqueFullName : вывести уникальные значения поля fullName всех элементов в коллекции");
        Send.send(s);
    }
}
