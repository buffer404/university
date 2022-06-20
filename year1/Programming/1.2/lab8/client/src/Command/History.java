package Command;

import Collection.Organization;
import Interface.Command;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Queue;

public class History implements Command, Serializable {
    private static final long serialVersionUID = 1234567L;
    String s;
    public Queue<String> data;
    public History(Queue<String> q){
        data=q;
    }
    @Override
    public void strat(ArrayDeque<Organization> collection, String user) throws IOException, ClassNotFoundException {
        System.out.println(
                        "help : вывести справку по доступным командам\n" +
                        "info : вывести в стандартный поток вывода информацию о коллекции \n" +
                        "history : вывести последние комманды \n" +
                        "averageOfAnnual_turnover : вывести среднее значение поля annualTurnover\n" +
                        "countLessThanAnnualTurnover: вывести количество элементов, значение поля annualTurnover которых больше 0\n" +
                        "printUniqueFullName : вывести уникальные значения поля fullName всех элементов в коллекции"
        );
    }
}
