package commands;

import managers.CollectionManager;
import managers.DBManager;
import models.StudyGroup;
import utility.Console;
import utility.ExecutionResponse;

/**
 * Класс команды для добавления нового элемента в коллекцию, если он меньше
 * минимального уже существующего в коллекции
 *
 * @author Andrew Schmunk
 * @version 1.0
 */
public class AddIfMin extends Command {
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
    public AddIfMin(Console console, CollectionManager collectionManager, DBManager dbManager) {
        super("add_if_min {element} ", "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
        this.console = console;
        this.collectionManager = collectionManager;
        this.dbManager = dbManager;
        commandType = CommandTypes.ADDIFMIN;
    }

    /**
     * Испольнение команды
     *
     * @param arguments массив с аргументами команды
     * @return возвращает ответ о выполнении команды
     */
    @Override
    public ExecutionResponse apply(String[] arguments,String login) {
        if (arguments[1].isEmpty())
            return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");
        StudyGroup d = StudyGroup.fromArray(arguments[1].split("/"));
        d.setId(dbManager.getFreeId());

        if (d != null && d.validate()) {
            if (collectionManager.getCollection().isEmpty()) {
                collectionManager.add(d);
                dbManager.insertGroup(d);
                return new ExecutionResponse("Учебная группа успешно добавлена!");
            } else {
                if (d.getStudentsCount() < collectionManager.getFirst().getStudentsCount()) {
                    collectionManager.add(d);
                    if(!dbManager.insertGroup(d)) return new ExecutionResponse(false, "При добавлении учебной группы возникза ошибка!");
                    return new ExecutionResponse("Учебная группа успешно добавлена!");
                } else return new ExecutionResponse("Учебная группа больше минимальной, добавление не произошло.");
            }
        } else return new ExecutionResponse(false, "Поля учебной группы не валидны! Учебная группа не создана!");

    }
}