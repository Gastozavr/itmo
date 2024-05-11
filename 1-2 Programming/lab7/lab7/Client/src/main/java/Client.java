
import commands.*;
import javafx.application.Application;
import managers.*;
import java.util.*;


import models.*;
import utility.ExecutionResponse;
import utility.Runner;
import utility.StandartConsole;

public class Client {


    public static void main(String[] args){
        var console = new StandartConsole();
        NetworkManager networkManager = new NetworkManager(20000);

        while (!networkManager.init(args)) {
        }
        Map<CommandTypes, String[]> commands = new HashMap<>();
        commands.put(CommandTypes.ADD, new String[]{"add {element}", "добавить новый элемент в коллекцию"});
        commands.put(CommandTypes.ADDIFMIN, new String[]{"add_if_min {element} ", "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции"});
        commands.put(CommandTypes.AVERAGEOFSTUDENTSCOUNT, new String[]{"average_of_students_count", "вывести среднее значение поля studentsCount для всех элементов коллекции"});
        commands.put(CommandTypes.CLEAR, new String[]{"clear", "очистить коллекцию"});
        commands.put(CommandTypes.EXIT, new String[]{"exit", "завершить программу"});
        commands.put(CommandTypes.HELP, new String[]{"help", "вывести справку по доступным командам"});
        commands.put(CommandTypes.INFO, new String[]{"info", "вывести информацию о коллекции"});
        commands.put(CommandTypes.MINBYGROUPADMIN, new String[]{"min_by_group_admin", "вывести любой объект из коллекции, значение поля groupAdmin которого является минимальным"});
        commands.put(CommandTypes.PRINTDESCENDING, new String[]{"print_descending", "вывести элементы коллекции в порядке убывания"});
        commands.put(CommandTypes.REMOVEBYID, new String[]{"remove_by_id <ID>", "удалить элемент из коллекции по ID"});
        commands.put(CommandTypes.REMOVEGREATER, new String[]{"remove_greater {element} ", "удалить из коллекции все элементы, превышающие заданный"});
        commands.put(CommandTypes.REMOVELOWER, new String[]{"remove_lower {element}", "удалить из коллекции все элементы, меньшие, чем заданный"});
        commands.put(CommandTypes.SHOW, new String[]{"show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении"});
        commands.put(CommandTypes.UPDATE, new String[]{"update <ID> {element}", "обновить значение элемента коллекции по ID"});
        commands.put(CommandTypes.EXECUTESCRIPT, new String[]{"execute_script <file_name>", "исполнить скрипт из указанного файла"});
        console.println("Добро пожаловать!");
        console.println("Для дальнейшей работы необходимо авторизоваться(login/register)!");
        console.println("После регистрации вы автоматически войдете в систему.");
        Runnable runnable1=()->{new Runner(networkManager,console, commands).interactiveMode();};
        new Thread(runnable1).start();


    }
}