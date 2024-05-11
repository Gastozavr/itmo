package commands;

import managers.CollectionManager;
import managers.DBManager;
import models.StudyGroup;
import utility.Console;
import utility.ExecutionResponse;

/**
 * Класс команды для добавления нового элемента в коллекцию
 *
 * @author Andrew Schmunk
 * @version 1.0
 */
public class Add extends Command {
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
    public Add(Console console, CollectionManager collectionManager, DBManager dbManager) {
        super("add {element}", "добавить новый элемент в коллекцию");
        this.console = console;
        this.collectionManager = collectionManager;
        this.dbManager = dbManager;
        commandType = CommandTypes.ADD;
    }

    /**
     * Исполнение команды
     *
     * @param arguments массив с аргументами команды
     * @return возвращает ответ о выполнении команды
     */
    @Override
    public ExecutionResponse apply(String[] arguments,String login) {

        if (arguments[1].isEmpty()) {
            return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");
        }
        StudyGroup d = StudyGroup.fromArray(arguments[1].split("/"));
        d.setId(dbManager.getFreeId());
        if (d != null && d.validate()) {
            if (!dbManager.insertGroup(d))
                return new ExecutionResponse(false, "При добавлении учебной группы возникза ошибка!");
            collectionManager.add(d);
            return new ExecutionResponse(true, "Учебная группа успешно добавлена!");
        } else {
            return new ExecutionResponse(false, "Поля учебной группы не валидны! Учебная группа не создана!");
        }

    }
}