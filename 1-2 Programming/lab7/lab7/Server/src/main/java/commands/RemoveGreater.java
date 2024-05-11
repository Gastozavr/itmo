package commands;

import managers.CollectionManager;
import managers.DBManager;
import models.StudyGroup;
import utility.Console;
import utility.ExecutionResponse;

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
    private final DBManager dbManager;

    /**
     * Конструктор
     *
     * @param console           консоль
     * @param collectionManager менеджер коллекции
     */
    public RemoveGreater(Console console, CollectionManager collectionManager, DBManager dbManager) {
        super("remove_greater {element} ", "удалить из коллекции все элементы, превышающие заданный");
        this.console = console;
        this.collectionManager = collectionManager;
        this.dbManager = dbManager;
        commandType = CommandTypes.REMOVEGREATER;
    }

    /**
     * Исполнение команды
     *
     * @param arguments массив с аргументами
     * @return возвращает ответ о выполнении команды
     */
    @Override
    public ExecutionResponse apply(String[] arguments, String owner) {
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
                var e = iterator.next();
                if(e.getOwner().equals(owner)) {
                    if (!dbManager.deleteById(e.getId()))
                        return new ExecutionResponse(false, "При удалении учебной группы возникла ошибка");
                    collectionManager.remove(e.getId());
                }
            } else {
                if (iterator.next().getId() == id) delete = true;
            }
        }
        collectionManager.sort();
        return new ExecutionResponse("Все большие учебные группы удалены!");
    }
}
