package itmo.programming.lab5.commands;

import itmo.programming.lab5.managers.CollectionManager;
import itmo.programming.lab5.models.StudyGroup;
import itmo.programming.lab5.utility.Console;
import itmo.programming.lab5.utility.ExecutionResponse;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

/**
 * Класс команды для вывода элементов коллекции в порядке убывания
 *
 * @author Andrew Schmunk
 * @version 1.0
 */
public class PrintDescending extends Command {
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
    public PrintDescending(Console console, CollectionManager collectionManager) {
        super("print_descending", "вывести элементы коллекции в порядке убывания");
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
        if (!arguments[1].isEmpty())
            return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");
        int length = collectionManager.getCollection().size();
        StudyGroup[] array = new StudyGroup[length];
        Iterator<StudyGroup> iterator = collectionManager.getCollection().iterator();
        for (int i = 0; i < length; i++) {
            array[i] = iterator.next();
        }
        Arrays.sort(array, Collections.reverseOrder());
        StringBuilder info = new StringBuilder();
        for (StudyGroup element : array) {
            info.append(element + "\n\n");
        }
        return new ExecutionResponse(info.toString().trim());
    }
}
