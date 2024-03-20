package itmo.programming.lab5.commands;

import itmo.programming.lab5.managers.CollectionManager;
import itmo.programming.lab5.utility.Console;
import itmo.programming.lab5.utility.ExecutionResponse;

/**
 * Класс команды для выхода из интерактивного режима без сохранения
 *
 * @author Andrew Schmunk
 * @version 1.0
 */
public class Exit extends Command {
    /**
     * Консоль
     */
    private final Console console;

    /**
     * Конструктор
     *
     * @param console консоль
     */
    public Exit(Console console) {
        super("exit", "завершить программу (без сохранения в файл)");
        this.console = console;
    }

    /**
     * Исполнение команды
     *
     * @param arguments массив с аргументами
     * @return возвращает ответ о выполнении команды
     */
    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (!arguments[1].isEmpty())
            return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");

        return new ExecutionResponse("exit"); //"Завершение выполнения...");
    }
}