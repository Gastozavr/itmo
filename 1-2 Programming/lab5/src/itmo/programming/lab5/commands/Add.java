package itmo.programming.lab5.commands;

import itmo.programming.lab5.managers.CollectionManager;
import itmo.programming.lab5.managers.AskManager;
import itmo.programming.lab5.models.StudyGroup;
import itmo.programming.lab5.utility.Console;
import itmo.programming.lab5.utility.ExecutionResponse;

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

    /**
     * Конструктор
     *
     * @param console           консоль
     * @param collectionManager менеджер коллекции
     */
    public Add(Console console, CollectionManager collectionManager) {
        super("add {element}", "добавить новый элемент в коллекцию");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Исполнение команды
     *
     * @param arguments массив с аргументами команды
     * @return возвращает ответ о выполнении команды
     */
    @Override
    public ExecutionResponse apply(String[] arguments) {
        try {
            if (!arguments[1].isEmpty())
                return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");
            console.println("* Создание новой учебной группы:");
            StudyGroup d = AskManager.askStudyGroup(console, collectionManager.getFreeId());

            if (d != null && d.validate()) {
                collectionManager.add(d);
                return new ExecutionResponse("Учебная группа успешно добавлен!");
            } else return new ExecutionResponse(false, "Поля учебной группы не валидны! Учебная группа не создана!");
        } catch (AskManager.AskBreak e) {
            return new ExecutionResponse(false, "Отмена...");
        }
    }
}