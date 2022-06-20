package start;

import Command.*;
import Except.Check;
import Except.Exception;
import Org.Organization;
import utility.Pharse;
import utility.Script;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

/**
 * Класс, через который происходит общение и работа пользователя с программой
 */
public class Run {
    /**
     * @param ArrayOrganizations Коллекция организаций
     * @param pharse             Парсер json
     * @param ArrayCommand       Список выполненных комманд
     * @param DateInitialization Датат инициализации
     * @param scanner            Класс, который определяет как мы будем считывать информацию
     */
    public Run(ArrayDeque<Organization> ArrayOrganizations, Pharse pharse, Queue<String> ArrayCommand, Date DateInitialization, Scanner scanner) {
        System.out.println("Введите комманду");
        try {
            String full_command = scanner.nextLine();
            String command = full_command.trim().split(" ")[0];

            while (!command.equals("exit")) {
                ArrayCommand.add(command);
                if (ArrayCommand.size() > 8) {
                    ArrayCommand.remove();
                }

                if (command.equals("save")) {
                    Save save = new Save(pharse, ArrayOrganizations);
                    save.Make_save();
                } else if (command.equals("help")) {
                    Help help = new Help();
                    help.show_help();
                } else if (command.equals("show")) {
                    Show show = new Show(ArrayOrganizations);
                    show.Make_show();
                } else if (command.equals("clear")) {
                    Clear clear = new Clear(ArrayOrganizations);
                    clear.Make_clear();
                } else if (command.equals("history")) {
                    History history = new History(ArrayCommand);
                    history.Show_history();
                } else if (command.equals("info")) {
                    Info info = new Info(ArrayOrganizations, DateInitialization);
                    info.Show_info();
                } else if (command.equals("remove_by_id")) {
                    System.out.println(full_command.trim().split(" ")[1]);
                    RemoveById remove_byId = new RemoveById(Integer.parseInt(full_command.trim().split(" ")[1]), ArrayOrganizations);
                    remove_byId.remove();
                } else if (command.equals("update")) {
                    RemoveById remove_byId = new RemoveById(Integer.parseInt(full_command.trim().split(" ")[1]), ArrayOrganizations);
                    remove_byId.remove();
                    Update update = new Update((Integer.parseInt(full_command.trim().split(" ")[1])), ArrayOrganizations, scanner);
                    update.Make_update();
                } else if (command.equals("add")) {
                    Add add = new Add(ArrayOrganizations, scanner);
                    add.add_new_organization();
                } else if (command.equals("remove_first")) {
                    RemoveFirst remove_first = new RemoveFirst(ArrayOrganizations);
                    remove_first.remove();
                } else if (command.equals("remove_lower")) {
                    RemoveLower remove_lower = new RemoveLower(Integer.parseInt(full_command.trim().split(" ")[1]), ArrayOrganizations);
                    remove_lower.remove();
                } else if (command.equals("count_less_than_annual_turnover")) {
                    CountLessThanAnnualTurnover count_lessThanAnnualTurnover = new CountLessThanAnnualTurnover(Integer.parseInt(full_command.trim().split(" ")[1]), ArrayOrganizations);
                    count_lessThanAnnualTurnover.Show_count_less_than_annual_turnover();
                } else if (command.equals("average_of_annual_turnover")) {
                    AverageOfAnnualTurnover average_ofAnnualTurnover = new AverageOfAnnualTurnover(ArrayOrganizations);
                    average_ofAnnualTurnover.Show_verage_of_annual_turnover();
                } else if (command.equals("print_unique_full_name")) {
                    PrintUniqueFullName print_uniqueFullName = new PrintUniqueFullName(ArrayOrganizations);
                    print_uniqueFullName.Show_full_name();
                } else if (command.equals("execute_script")) {
                    Path path = Paths.get(full_command.trim().split(" ")[1]);
                    boolean isRegularExecutableFile = Files.isRegularFile(path) &
                            Files.isReadable(path) & Files.isExecutable(path);
                    if (isRegularExecutableFile) {
                        try {
                            List<String> lines = Files.readAllLines(path);
                            Files.write(Paths.get(full_command.trim().split(" ")[1]), "\nexit".getBytes(), StandardOpenOption.APPEND);
                            File file = new File(full_command.trim().split(" ")[1]);
                            if (Check.CheckScript(file.getAbsolutePath())) {
                                Script script = new Script(full_command.trim().split(" ")[1], lines, file);
                                script.execute();
                            } else {
                                System.err.println("Рекурсия");
                                Exception.err();
                                full_command = "exit";
                            }
                        } catch (IOException ignored) {
                        }

                    } else {
                        System.err.println("У вас нет доступа к данному файлу");
                    }
                } else {
                    System.out.println("Неверный ввод команды, чтобы узнать возможные команды, введите -help-");
                    System.out.println("Введите команду");
                }
                while ((full_command = scanner.nextLine()).equals("")) {
                }
                command = full_command.trim().split(" ")[0];


            }
        } catch (NoSuchElementException ignored) {
        }
    }
}
