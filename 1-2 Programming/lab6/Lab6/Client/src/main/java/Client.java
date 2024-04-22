
import commands.*;
import managers.*;

import java.io.IOException;
import java.util.*;


import utility.Runner;
import utility.StandartConsole;

public class Client {
    public static void main(String[] args) throws AskManager.AskBreak, IOException {
        var console = new StandartConsole();
        NetworkManager networkManager = new NetworkManager(8000);

        while (!networkManager.init(args)) {}
        Map<CommandTypes,String[]> commands = new HashMap<>();
        commands.put(CommandTypes.Add,new String[]{"add {element}", "добавить новый элемент в коллекцию"});
        commands.put(CommandTypes.AddIfMin,new String[]{"add_if_min {element} ", "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции"});
        commands.put(CommandTypes.AverageStudentsCount, new String[]{"average_of_students_count", "вывести среднее значение поля studentsCount для всех элементов коллекции"});
        commands.put(CommandTypes.Clear, new String[]{"clear", "очистить коллекцию"});
        commands.put(CommandTypes.Exit, new String[]{"exit", "завершить программу"});
        commands.put(CommandTypes.Help,new String[]{"help", "вывести справку по доступным командам"});
        commands.put(CommandTypes.Info,new String[]{"info", "вывести информацию о коллекции"});
        commands.put(CommandTypes.MinByGroupAdmin,new String[]{"min_by_group_admin", "вывести любой объект из коллекции, значение поля groupAdmin которого является минимальным"});
        commands.put(CommandTypes.PrintDescending,new String[]{"print_descending", "вывести элементы коллекции в порядке убывания"});
        commands.put(CommandTypes.RemoveById,new String[]{"remove_by_id <ID>", "удалить элемент из коллекции по ID"});
        commands.put(CommandTypes.RemoveGreater,new String[]{"remove_greater {element} ", "удалить из коллекции все элементы, превышающие заданный"});
        commands.put(CommandTypes.RemoveLower,new String[]{"remove_lower {element}", "удалить из коллекции все элементы, меньшие, чем заданный"});
        commands.put(CommandTypes.Show,new String[]{"show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении"});
        commands.put(CommandTypes.Update,new String[]{"update <ID> {element}", "обновить значение элемента коллекции по ID"});

        new Runner(networkManager,console, commands).interactiveMode();

    }
}