package managers;

import commands.Command;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Класс менеджера коллекции
 *
 * @author Andrew Schmunk
 * @version 1.0
 */
public class CommandManager implements Serializable {
    private static final long serialVersionUID = 19L;
    /**
     * Словарь для хранения команд и их названий
     */
    private final Map<String, Command> commands = new LinkedHashMap<>();

    /**
     * Функция регистрации команды в менеджере команд
     *
     * @param commandName
     * @param command
     */
    public void register(String commandName, Command command) {
        commands.put(commandName, command);
    }

    /**
     * Функция для получения всех добавленных в менеджер команд
     *
     * @return возвращает все команды, хранящиеся в менеджере
     */
    public Map<String, Command> getCommands() {
        return commands;
    }
}
