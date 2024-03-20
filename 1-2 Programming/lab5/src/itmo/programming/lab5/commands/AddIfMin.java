package itmo.programming.lab5.commands;

import itmo.programming.lab5.managers.CollectionManager;
import itmo.programming.lab5.managers.AskManager;
import itmo.programming.lab5.models.StudyGroup;
import itmo.programming.lab5.utility.Console;
import itmo.programming.lab5.utility.ExecutionResponse;

/**
 * Класс команды для добавления нового элемента в коллекцию, если он меньше
 * минимального уже существующего в коллекции
 * @author Andrew Schmunk
 * @version 1.0
 */
public class AddIfMin extends Command {
    /** Консоль */
    private final Console console;
    /** Менеджер коллекции */
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
    }

    /**
     * Испольнение команды
     * @param arguments массив с аргументами команды
     * @return возвращает ответ о выполнении команды
     */
    @Override
    public ExecutionResponse apply(String[] arguments) {
        try {
            if (!arguments[1].isEmpty())
                return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");

            console.println("* Создание нового Дракона:");
            StudyGroup d = AskManager.askStudyGroup(console, collectionManager.getFreeId());

            if (d != null && d.validate()) {
                if (d.getStudentsCount() < collectionManager.getFirst().getStudentsCount()) {
                    collectionManager.add(d);
                    return new ExecutionResponse("Учебная группа успешно добавлена!");
                }
                else return new ExecutionResponse("Учебная группа больше минимальной, добавление не произошло.");
            } else return new ExecutionResponse(false, "Поля учебной группы не валидны! Учебная группа не создана!");
        } catch (AskManager.AskBreak e) {
            return new ExecutionResponse(false, "Отмена...");
        }
    }
}