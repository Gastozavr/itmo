package itmo.programming.lab5.commands;

import itmo.programming.lab5.managers.CollectionManager;
import itmo.programming.lab5.models.StudyGroup;
import itmo.programming.lab5.utility.Console;
import itmo.programming.lab5.utility.ExecutionResponse;

/**
 * Класс команды для вывода коллекции с минимальным админом
 *
 * @author Andrew Schmunk
 * @version 1.0
 */
public class MinByGroupAdmin extends Command {
    /**
     * Консоль
     */
    private final Console console;
    /**
     * Менеджер коллекции
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор
     *
     * @param console           консоль
     * @param collectionManager менеджер коллекции
     */
    public MinByGroupAdmin(Console console, CollectionManager collectionManager) {
        super("min_by_group_admin", "вывести любой объект из коллекции, значение поля groupAdmin которого является минимальным");
        this.console = console;
        this.collectionManager = collectionManager;
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
        StudyGroup min = collectionManager.getFirst();
        for (var e : collectionManager.getCollection()) {
            if (min.getGroupAdmin().getHeight() > e.getGroupAdmin().getHeight()) min = e;
        }
        return new ExecutionResponse(min.toString());
    }
}

