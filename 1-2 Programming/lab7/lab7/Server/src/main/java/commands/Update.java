package commands;

import managers.CollectionManager;
import managers.DBManager;
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
    private final DBManager dbManager;
    /**
     * Конструктор
     *
     * @param console           консоль
     * @param collectionManager менеджер коллекции
     */

    public Update(Console console, CollectionManager collectionManager,DBManager dbManager) {
        super("update <ID> {element}", "обновить значение элемента коллекции по ID");
        this.console = console;
        this.collectionManager = collectionManager;
        this.dbManager = dbManager;
        commandType = CommandTypes.UPDATE;
    }

    /**
     * Исполнение команды
     *
     * @param arguments массив с аргументами
     * @return возвращает ответ о выполнении команды
     */
    @Override
    public ExecutionResponse apply(String[] arguments,String login) {
        if (arguments[1].isEmpty())
            return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");
        StudyGroup d = StudyGroup.fromArray(arguments[1].split("/"));
        long id = d.getId();
        var old = collectionManager.getById(id);
        if (old == null || !collectionManager.getCollection().contains(old)) {
            return new ExecutionResponse(false, "Не существующий ID");
        }
        if(!old.getOwner().equals(login)) {return new ExecutionResponse(false,"У вас нет прав для обновления этого элемента");}
        if (d != null && d.validate()) {
            collectionManager.remove(old.getId());
            collectionManager.add(d);
            collectionManager.sort();
            if(dbManager.update(old.getId(),d)) return new ExecutionResponse("Обновлено!");
            else return new ExecutionResponse(false,"Не удалось записать в базу данных!");
        } else {
            return new ExecutionResponse(false, "Поля учебной группы не валидны! Учебная группа не создана!");
        }

    }
}