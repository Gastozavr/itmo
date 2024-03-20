package itmo.programming.lab5.commands;

import itmo.programming.lab5.managers.CollectionManager;
import itmo.programming.lab5.utility.Console;
import itmo.programming.lab5.managers.AskManager;
import itmo.programming.lab5.utility.ExecutionResponse;

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
    }

    /**
     * Исполнение команды
     *
     * @param arguments массив с аргументами
     * @return возвращает ответ о выполнении команды
     */
    @Override
    public ExecutionResponse apply(String[] arguments) {
        try {
            if (arguments[1].isEmpty())
                return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");
            long id = -1;
            try {
                id = Long.parseLong(arguments[1].trim());
            } catch (NumberFormatException e) {
                return new ExecutionResponse(false, "ID не распознан");
            }

            var old = collectionManager.getById(id);
            if (old == null || !collectionManager.getCollection().contains(old)) {
                return new ExecutionResponse(false, "Не существующий ID");
            }

            console.println("* Создание новой учебной группы:");
            var d = AskManager.askStudyGroup(console, old.getId());
            if (d != null && d.validate()) {
                collectionManager.remove(old.getId());
                collectionManager.add(d);
                collectionManager.sort();
                return new ExecutionResponse("Обновлено!");
            } else {
                return new ExecutionResponse(false, "Поля учебной группы не валидны! Учебная группа не создана!");
            }
        } catch (AskManager.AskBreak e) {
            return new ExecutionResponse(false, "Поля учебной группы не валидны! Учебная группа не создан!");
        }
    }
}