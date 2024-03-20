package itmo.programming.lab5.commands;

import itmo.programming.lab5.managers.CollectionManager;
import itmo.programming.lab5.models.StudyGroup;
import itmo.programming.lab5.utility.Console;
import itmo.programming.lab5.utility.ExecutionResponse;

import java.util.Iterator;

/**
 * Класс команды для удаления элементов, больших введенного
 *
 * @author Andrew Schmunk
 * @version 1.0
 */
public class RemoveGreater extends Command {
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
    public RemoveGreater(Console console, CollectionManager collectionManager) {
        super("remove_greater {element} ", "удалить из коллекции все элементы, превышающие заданный");
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
        boolean delete = false;
        Iterator<StudyGroup> iterator = collectionManager.getCollection().iterator();
        while (iterator.hasNext()) {
            if (delete) {
                collectionManager.remove(iterator.next().getId());
            } else {
                if (iterator.next().getId() == id) delete = true;
            }
        }
        collectionManager.sort();
        return new ExecutionResponse("Все большие учебные группы удалены!");
    }
}
