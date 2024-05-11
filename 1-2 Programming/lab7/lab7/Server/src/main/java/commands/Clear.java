package commands;

import managers.CollectionManager;
import managers.DBManager;
import utility.Console;
import models.StudyGroup;
import utility.ExecutionResponse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

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
    private final DBManager dbManager;
    /**
     * Конструктор
     *
     * @param console           консоль
     * @param collectionManager менеджер коллекции
     */
    public Clear(Console console, CollectionManager collectionManager, DBManager dbManager) {
        super("clear", "очистить коллекцию");
        this.console = console;
        this.collectionManager = collectionManager;
        this.dbManager = dbManager;
        commandType=CommandTypes.CLEAR;
    }

    /**
     * Исполнение команды
     *
     * @param arguments массив с аргументами команды
     * @return возвращает ответ о выполнении команды
     */
    @Override
    public ExecutionResponse apply(String[] arguments,String login) {
        if (!arguments[1].isEmpty())
            return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");
        Iterator<StudyGroup> iterator = collectionManager.getCollection().iterator();
        CopyOnWriteArrayList<Long> ids = new CopyOnWriteArrayList<>();
        while (iterator.hasNext()) {
            StudyGroup group = iterator.next();
            ids.add(group.getId());

        }
        for(var e: ids){
            collectionManager.remove(e);
        }
        collectionManager.sort();
        dbManager.clear(login);
        return new ExecutionResponse("Коллекция очищена!");
    }

}