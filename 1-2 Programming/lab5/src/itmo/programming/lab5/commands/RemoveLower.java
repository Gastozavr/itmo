package itmo.programming.lab5.commands;

import itmo.programming.lab5.managers.CollectionManager;
import itmo.programming.lab5.models.StudyGroup;
import itmo.programming.lab5.utility.Console;
import itmo.programming.lab5.utility.ExecutionResponse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Класс команды для удаления элементов, меньших введенного
 *
 * @author Andrew Schmunk
 * @version 1.0
 */
public class RemoveLower extends Command {
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

    public RemoveLower(Console console, CollectionManager collectionManager) {
        super("remove_lower {element}", "удалить из коллекции все элементы, меньшие, чем заданный");
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
        if (arguments[1].isEmpty())
            return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");
        long id = -1;
        try {
            id = Long.parseLong(arguments[1].trim());
        } catch (NumberFormatException e) {
            return new ExecutionResponse(false, "ID не распознан");
        }

        if (collectionManager.getById(id) == null || !collectionManager.getCollection().contains(collectionManager.getById(id)))
            return new ExecutionResponse(false, "Не существующий ID");
        Iterator<StudyGroup> iterator = collectionManager.getCollection().iterator();
        List<Long> govno = new ArrayList<>();
        while (iterator.hasNext()) {
            var e = iterator.next();
            if (e.getId() == id) break;
            else {
                govno.add(e.getId());
            }
        }
        for (var e : govno) {
            collectionManager.remove(e);
        }

        collectionManager.sort();
        return new ExecutionResponse("Все меньшие учебные группы удалены!");
    }
}

