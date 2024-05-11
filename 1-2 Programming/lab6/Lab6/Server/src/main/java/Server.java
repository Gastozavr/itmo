import commands.*;
import managers.CollectionManager;
import managers.CommandManager;
import managers.DumpManager;
import managers.NetworkManager;
import utility.Console;
import utility.ExecutionResponse;
import utility.StandartConsole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Server {
    public static final Logger logger = LoggerFactory.getLogger(Server.class);
    static String[] userCommand = new String[2];
    static byte arr[] = new byte[5069];
    static int len = arr.length;

    public static void main(String[] args) {
        var console = new StandartConsole();
        if (args.length == 0) {
            console.println("Введите имя загружаемого файла как аргумент командной строки");
            logger.info("Не введено название файла. Сервер не был запушен.");
            System.exit(1);
        }
        logger.info("Сервер успешно запущен!");
        var dumpManager = new DumpManager(args[0], console);
        var collectionManager = new CollectionManager(dumpManager);

        if (!collectionManager.loadCollection()) {
            logger.info("Не удалось загрузить коллекцию.");
            System.exit(1);

        }
        var networkManager = new NetworkManager(17534, 800);
        while (!networkManager.init()) {
            logger.info("Менеджер сетевого взаимодействия инициализирован!");
        }
        collectionManager.sort();
        var commandManager = new CommandManager() {{
            register("info", new Info(console, collectionManager));
            register("show", new Show(console, collectionManager));
            register("add", new Add(console, collectionManager));
            register("update", new Update(console, collectionManager));
            register("remove_by_id", new RemoveById(console, collectionManager));
            register("clear", new Clear(console, collectionManager));
            register("save", new Save(console, collectionManager));
            register("print_descending", new PrintDescending(console, collectionManager));
            register("remove_greater", new RemoveGreater(console, collectionManager));
            register("remove_lower", new RemoveLower(console, collectionManager));
            register("average_of_students_count", new AverageStudentsCount(console, collectionManager));
            register("min_by_group_admin", new MinByGroupAdmin(console, collectionManager));
            register("add_if_min", new AddIfMin(console, collectionManager));
        }};
        run(networkManager, console, commandManager);
    }

    public static void run(NetworkManager networkManager, Console console, CommandManager commandManager) {
        while (true) {
            arr = networkManager.receiveData(len);
            Container commandd = NetworkManager.deserialize(arr);
            if (commandd != null) {
                userCommand[0] = commandd.getCommandType().Type();
                userCommand[1] = commandd.getArgs();
                var command = commandManager.getCommands().get(userCommand[0]);
                ExecutionResponse response;
                if (userCommand[0].equals("")) response = new ExecutionResponse("");
                if (command == null)
                    response = new ExecutionResponse(false, "Команда '" + userCommand[0] + "' не найдена. Наберите 'help' для справки");
                else response = command.apply(userCommand);
                logger.info("Команда обработана!");
                networkManager.sendData(NetworkManager.serializer(response));
                logger.info("Отправлен ответ серверу!");
            }

        }
    }
}
