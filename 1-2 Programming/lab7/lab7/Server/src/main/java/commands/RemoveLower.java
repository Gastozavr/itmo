package commands;

import managers.CollectionManager;
import managers.DBManager;
import models.StudyGroup;
import utility.Console;
import utility.ExecutionResponse;

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
    private final DBManager dbManager;

    public RemoveLower(Console console, CollectionManager collectionManager, DBManager dbManager) {
        super("remove_lower {element}", "удалить из коллекции все элементы, меньшие, чем заданный");
        this.console = console;
        this.collectionManager = collectionManager;
        this.dbManager = dbManager;
        commandType = CommandTypes.REMOVELOWER;
    }

    /**
     * Исполнение команды
     *
     * @param arguments массив с аргументами
     * @return возвращает ответ о выполнении команды
     */
    @Override
    public ExecutionResponse apply(String[] arguments, String login) {
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
                if (e.getOwner().equals(login))
                    govno.add(e.getId());
            }
        }
        for (var e : govno) {
            if (!dbManager.deleteById(e))
                return new ExecutionResponse(false, "При удалении учебной группы возникла ошибка");
            collectionManager.remove(e);
        }
        collectionManager.sort();
        return new ExecutionResponse("Все меньшие учебные группы удалены!");
    }
}

