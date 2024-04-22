package commands;

import managers.CollectionManager;
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

    /**
     * Конструктор
     *
     * @param console           консоль
     * @param collectionManager менеджер коллекции
     */
    public AddIfMin(Console console, CollectionManager collectionManager) {
        super("add_if_min {element} ", "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
        this.console = console;
        this.collectionManager = collectionManager;
        commandType = CommandTypes.AddIfMin;
    }

    /**
     * Испольнение команды
     *
     * @param arguments массив с аргументами команды
     * @return возвращает ответ о выполнении команды
     */
    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (arguments[1].isEmpty())
            return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");

        console.println("* Создание новогой учебной группы:");
        StudyGroup d = StudyGroup.fromArray(arguments[1].split("/"));
        d.setId(collectionManager.getFreeId());

        if (d != null && d.validate()) {
            if (d.getStudentsCount() < collectionManager.getFirst().getStudentsCount()) {
                collectionManager.add(d);
                return new ExecutionResponse("Учебная группа успешно добавлена!");
            } else return new ExecutionResponse("Учебная группа больше минимальной, добавление не произошло.");
        } else return new ExecutionResponse(false, "Поля учебной группы не валидны! Учебная группа не создана!");

    }
}