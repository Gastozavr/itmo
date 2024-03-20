package itmo.programming.lab5.commands;

import itmo.programming.lab5.managers.CollectionManager;
import itmo.programming.lab5.utility.Console;
import itmo.programming.lab5.utility.ExecutionResponse;
/**
 * Класс команды для выполнения скрипта из файла
 * @author Andrew Schmunk
 * @version 1.0
 */
public class ExecuteScript extends Command {
    /** Консоль */
    private final Console console;

    /**
     * Конструктор
     *
     * @param console консоль
     */
    public ExecuteScript(Console console) {
        super("execute_script <file_name>", "исполнить скрипт из указанного файла");
        this.console = console;
    }

    /**
     *Исполнение команды
     * @param arguments массив с аргументами
     * @return возвращает ответ о выполнении команды
     */
    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (arguments[1].isEmpty()) return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");

        return new ExecutionResponse("Выполнение скрипта '" + arguments[1] + "'...");
    }
}