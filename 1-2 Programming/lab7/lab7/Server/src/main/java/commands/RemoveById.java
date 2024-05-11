package commands;

import managers.CollectionManager;
import managers.DBManager;
import utility.Console;
import utility.ExecutionResponse;

/**
 * Класс команды для удаления элемента из коллекции по его id
 *
 * @author Andrew Schmunk
 * @version 1.0
 */
public class RemoveById extends Command  {
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
    public RemoveById(Console console, CollectionManager collectionManager, DBManager dbManager) {
        super("remove_by_id <ID>", "удалить элемент из коллекции по ID");
        this.console = console;
        this.collectionManager = collectionManager;
        this.dbManager = dbManager;
        commandType=CommandTypes.REMOVEBYID;
    }

    /**
     * Исполнение команды
     *
     * @param arguments массив с аргументами
     * @return возвращает ответ о выполнении команды
     */
    @Override
    public ExecutionResponse apply(String[] arguments,String login) {
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
        if(!collectionManager.getById(id).getOwner().equals(login)) return new ExecutionResponse(false,"У вас нет прав для удаления данного элемента");
        if (!dbManager.deleteById(id))
            return new ExecutionResponse(false, "При удалении учебной группы возникла ошибка");
        collectionManager.remove(id);
        collectionManager.sort();

        return new ExecutionResponse("Учебная группа успешно удалена!");
    }
}