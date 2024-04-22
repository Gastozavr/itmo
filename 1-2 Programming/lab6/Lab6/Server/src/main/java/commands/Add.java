package commands;

import managers.CollectionManager;
import models.StudyGroup;
import utility.Console;
import utility.ExecutionResponse;

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
        commandType = CommandTypes.Add;
    }

    /**
     * Исполнение команды
     *
     * @param arguments массив с аргументами команды
     * @return возвращает ответ о выполнении команды
     */
    @Override
    public ExecutionResponse apply(String[] arguments) {

        if (arguments[1].isEmpty()){
            System.out.println("gggg");
            return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");}
        console.println("* Создание новой учебной группы:");
        for(var e:arguments[1].split("/")){
            System.out.println(e);
        }
        StudyGroup d = StudyGroup.fromArray(arguments[1].split("/"));
        d.setId(collectionManager.getFreeId());
        if (d != null && d.validate()) {
            collectionManager.add(d);
            System.out.println("lll");
            return new ExecutionResponse(true,"Учебная группа успешно добавлен!");
        }
        else {
            System.out.println("gggg");
            return new ExecutionResponse(false, "Поля учебной группы не валидны! Учебная группа не создана!");
        }

    }
}