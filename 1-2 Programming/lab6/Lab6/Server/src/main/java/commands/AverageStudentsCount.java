package commands;

import managers.CollectionManager;
import models.StudyGroup;
import utility.Console;
import utility.ExecutionResponse;

/**
 * Класс команды для вывода в стандартный поток вывода среднего количества
 * студентов в учебных группах
 *
 * @author Andrew Schmunk
 * @version 1.0
 */
public class AverageStudentsCount extends Command  {
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
        commandType=CommandTypes.AverageStudentsCount;
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
        float avg = (float) collectionManager.getCollection().stream().mapToInt(StudyGroup::getStudentsCount).average().orElse(Double.NaN);
        return new ExecutionResponse("Среднее количество студентов в учебных группах:" + Float.toString(avg));
    }
}
