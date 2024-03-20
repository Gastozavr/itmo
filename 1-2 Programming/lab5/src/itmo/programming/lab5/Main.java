package itmo.programming.lab5;

import itmo.programming.lab5.commands.*;
import itmo.programming.lab5.managers.AskManager;
import itmo.programming.lab5.managers.CommandManager;
import itmo.programming.lab5.managers.DumpManager;
import itmo.programming.lab5.models.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import itmo.programming.lab5.managers.CollectionManager;
import itmo.programming.lab5.utility.Console;
import itmo.programming.lab5.utility.Runner;
import itmo.programming.lab5.utility.StandartConsole;

public class Main {
    public static void main(String[] args) throws AskManager.AskBreak {
        var console = new StandartConsole();

        if (args.length == 0) {
            console.println("Введите имя загружаемого файла как аргумент командной строки");
            System.exit(1);
        }

        var dumpManager = new DumpManager(args[0], console);
        var collectionManager = new CollectionManager(dumpManager);
        if (!collectionManager.loadCollection()) {
            System.exit(1);
        }
        collectionManager.sort();
        var commandManager = new CommandManager() {{
            register("help", new Help(console, this));
            register("info", new Info(console, collectionManager));
            register("show", new Show(console, collectionManager));
            register("add", new Add(console, collectionManager));
            register("update", new Update(console, collectionManager));
            register("remove_by_id", new RemoveById(console, collectionManager));
            register("clear", new Clear(console, collectionManager));
            register("save", new Save(console, collectionManager));
            register("execute_script", new ExecuteScript(console));
            register("exit", new Exit(console));
            register("print_descending", new PrintDescending(console, collectionManager));
            register("remove_greater", new RemoveGreater(console, collectionManager));
            register("remove_lower", new RemoveLower(console, collectionManager));
            register("average_of_students_count", new AverageStudentsCount(console, collectionManager));
            register("min_by_group_admin", new MinByGroupAdmin(console, collectionManager));
            register("add_if_min", new AddIfMin(console, collectionManager));
        }};

        new Runner(console, commandManager).interactiveMode();
    }
}