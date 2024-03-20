package itmo.programming.lab5.commands;

import itmo.programming.lab5.managers.CollectionManager;
import itmo.programming.lab5.utility.Console;
import itmo.programming.lab5.utility.ExecutionResponse;

/**
 * Класс команды для вывода в стандартный поток вывода среднего количества
 * студентов в учебных группах
 *
 * @author Andrew Schmunk
 * @version 1.0
 */
public class AverageStudentsCount extends Command {
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
    public AverageStudentsCount(Console console, CollectionManager collectionManager) {
        super("average_of_students_count", "вывести среднее значение поля studentsCount для всех элементов коллекции");
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
        if (!arguments[1].isEmpty())
            return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");
        var count = collectionManager.getCollection().size();
        if (count == 0) return new ExecutionResponse(false, "Коллекция пуста!");
        int sum = 0;
        for (var e : collectionManager.getCollection())
            sum += e.getStudentsCount();

        return new ExecutionResponse("Среднее количество студентов в учебных группах:" + Float.toString(sum / count));
    }
}
