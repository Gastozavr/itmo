package commands;

import managers.CollectionManager;
import models.StudyGroup;
import utility.Console;
import utility.ExecutionResponse;

/**
 * Класс команды для обновления элемента коллекции
 *
 * @author Andrew Schmunk
 * @version 1.0
 */
public class Update extends Command {
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

    public Update(Console console, CollectionManager collectionManager) {
        super("update <ID> {element}", "обновить значение элемента коллекции по ID");
        this.console = console;
        this.collectionManager = collectionManager;
        commandType = CommandTypes.Update;
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
        StudyGroup d = StudyGroup.fromArray(arguments[1].split("/"));
        long id = d.getId();

        System.out.println(id);
        for (var e : arguments[1].split("/")) {
            System.out.println(e);
        }

        var old = collectionManager.getById(id);
        System.out.println(old);
        System.out.println(collectionManager);
        if (old == null || !collectionManager.getCollection().contains(old)) {
            return new ExecutionResponse(false, "Не существующий ID");
        }

        console.println("* Создание новой учебной группы:");

        if (d != null && d.validate()) {
            collectionManager.remove(old.getId());
            collectionManager.add(d);
            collectionManager.sort();
            return new ExecutionResponse("Обновлено!");
        } else {
            return new ExecutionResponse(false, "Поля учебной группы не валидны! Учебная группа не создана!");
        }

    }
}