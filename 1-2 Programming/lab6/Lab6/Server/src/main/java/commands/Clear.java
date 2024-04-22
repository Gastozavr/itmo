package commands;

import managers.CollectionManager;
import utility.Console;
import models.StudyGroup;
import utility.ExecutionResponse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Класс команды для очищения коллекции
 *
 * @author Andrew Schmunk
 * @version 1.0
 */
public class Clear extends Command  {
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
    public Clear(Console console, CollectionManager collectionManager) {
        super("clear", "очистить коллекцию");
        this.console = console;
        this.collectionManager = collectionManager;
        commandType=CommandTypes.Clear;
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
        System.out.println("dfsff");
        Iterator<StudyGroup> iterator = collectionManager.getCollection().iterator();
        List<Long> ids = new ArrayList<>();
        while (iterator.hasNext()) {
            StudyGroup group = iterator.next();
            ids.add(group.getId());

        }
        for(var e: ids){
            collectionManager.remove(e);
        }
        collectionManager.sort();
        return new ExecutionResponse("Коллекция очищена!");
    }

}