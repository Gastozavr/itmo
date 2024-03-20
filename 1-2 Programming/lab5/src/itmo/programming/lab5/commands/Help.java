package itmo.programming.lab5.commands;

import itmo.programming.lab5.managers.CollectionManager;
import itmo.programming.lab5.utility.Console;
import itmo.programming.lab5.managers.CommandManager;
import itmo.programming.lab5.utility.ExecutionResponse;

import java.util.stream.Collectors;

/**
 * Класс команды для вывода справки по всем доступным командам
 *
 * @author Andrew Schmunk
 * @version 1.0
 */
public class Help extends Command {
    /**
     * Консоль
     */
    private final Console console;
    /**
     * Менеджер комманд
     */
    private final CommandManager commandManager;

    /**
     * Конструктор
     *
     * @param console        консоль
     * @param commandManager менеджер команд
     */
    public Help(Console console, CommandManager commandManager) {
        super("help", "вывести справку по доступным командам");
        this.console = console;
        this.commandManager = commandManager;
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

        return new ExecutionResponse(commandManager.getCommands().values().stream().map(command -> String.format(" %-35s%-1s%n", command.getName(), command.getDescription())).collect(Collectors.joining("\n")));
    }
}