package itmo.programming.lab5.commands;

import itmo.programming.lab5.utility.Console;
import itmo.programming.lab5.managers.CollectionManager;

import java.time.LocalDateTime;

import itmo.programming.lab5.utility.ExecutionResponse;

/**
 * Класс команды для получения информации о коллекции
 *
 * @author Andrew Schmunk
 * @version 1.0
 */
public class Info extends Command {
    /**
     * Консоль
     */
    private final Console console;
    /**
     * Менеджер комманд
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор
     *
     * @param console           консоль
     * @param collectionManager менеджер коллекции
     */
    public Info(Console console, CollectionManager collectionManager) {
        super("info", "вывести информацию о коллекции");
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

        LocalDateTime lastInitTime = collectionManager.getLastInitTime();
        String lastInitTimeString = (lastInitTime == null) ? "в данной сессии инициализации еще не происходило" :
                lastInitTime.toLocalDate().toString() + " " + lastInitTime.toLocalTime().toString();

        LocalDateTime lastSaveTime = collectionManager.getLastSaveTime();
        String lastSaveTimeString = (lastSaveTime == null) ? "в данной сессии сохранения еще не происходило" :
                lastSaveTime.toLocalDate().toString() + " " + lastSaveTime.toLocalTime().toString();

        var s = "Сведения о коллекции:\n";
        s += " Тип: " + collectionManager.getCollection().getClass().toString() + "\n";
        s += " Количество элементов: " + collectionManager.getCollection().size() + "\n";
        s += " Дата последнего сохранения: " + lastSaveTimeString + "\n";
        s += " Дата последней инициализации: " + lastInitTimeString;
        return new ExecutionResponse(s);
    }
}