import commands.*;
import managers.*;
import models.*;
import utility.Console;
import utility.ExecutionResponse;
import utility.Record;
import utility.StandartConsole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.SocketAddress;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static final Logger logger = LoggerFactory.getLogger(Server.class);
    static String[] userCommand = new String[2];
    static byte arr[] = new byte[5069];
    static int len = arr.length;
    private static ExecutorService readingPool = Executors.newFixedThreadPool(1000);
    private static ExecutorService sendPool = Executors.newCachedThreadPool();

    public static void main(String[] args) {


        var console = new StandartConsole();
        if (args.length == 0) {
            logger.error("Не введен файл конфигураии. Сервер не запущен!");
            System.exit(1);
        }
        DBManager dbManager = new DBManager(args);
        dbManager.connect();
        var collectionManager = new CollectionManager(dbManager);

        if (!collectionManager.loadCollection()) {
            logger.error("Не удалось загрузить коллекцию.");
            System.exit(1);

        }
        var networkManager = new NetworkManager(17534, 800);
        while (!networkManager.init()) {
        }
        logger.info("Менеджер сетевого взаимодействия инициализирован!");
        logger.info("Сервер успешно запущен!");
        var commandManager = new CommandManager() {{
            register("info", new Info(console, collectionManager));
            register("show", new Show(console, collectionManager));
            register("add", new Add(console, collectionManager, dbManager));
            register("update", new Update(console, collectionManager, dbManager));
            register("remove_by_id", new RemoveById(console, collectionManager, dbManager));
            register("clear", new Clear(console, collectionManager, dbManager));
            register("print_descending", new PrintDescending(console, collectionManager));
            register("remove_greater", new RemoveGreater(console, collectionManager, dbManager));
            register("remove_lower", new RemoveLower(console, collectionManager, dbManager));
            register("average_of_students_count", new AverageStudentsCount(console, collectionManager));
            register("min_by_group_admin", new MinByGroupAdmin(console, collectionManager));
            register("add_if_min", new AddIfMin(console, collectionManager, dbManager));
            register("login", new Login(dbManager));
            register("register", new Register(dbManager));
        }};
        run(networkManager, commandManager, dbManager);
    }

    public static void run(NetworkManager networkManager, CommandManager commandManager, DBManager dbManager) {
        while (true) {
            Record rec = networkManager.receiveData(len);
            if (rec != null) {
                readingPool.submit(() -> {
                    Container commandd = NetworkManager.deserialize(rec.getArr());
                    if (commandd != null) {
                        execute(commandd, rec.getAddr(), commandManager, networkManager, dbManager);
                    }
                });
            }
        }
    }

    public static void execute(Container commandd, SocketAddress address, CommandManager
            commandManager, NetworkManager networkManager, DBManager dbManager) {
        Runnable runnable =
                () -> {
                    userCommand[0] = commandd.getCommandType().Type();
                    userCommand[1] = commandd.getArgs();
                    var command = commandManager.getCommands().get(userCommand[0]);
                    ExecutionResponse response;
                    if (userCommand[0].equals("")) response = new ExecutionResponse("");
                    if (command == null)
                        response = new ExecutionResponse(false, "Команда '" + userCommand[0] + "' не найдена. Наберите 'help' для справки");
                    else {
                        if (!userCommand[0].equals("login") & !userCommand[0].equals("register")) {
                            if (dbManager.exists(commandd.getLogin(), commandd.getPassword())) {
                                response = command.apply(userCommand, commandd.getLogin());
                            } else response = new ExecutionResponse(false, "Неверный логин или пароль");
                        } else {
                            response = command.apply(userCommand, commandd.getLogin());
                        }

                    }
                    logger.info("Команда обработана!");
                    if (response != null) {
                        sendData(response, address, networkManager);
                    } else {
                        sendData(new ExecutionResponse(false, "Не удалось выполнить команду"), address, networkManager);
                    }


                };
        new Thread(runnable).start();
    }


    public static void sendData(ExecutionResponse response, SocketAddress address, NetworkManager networkManager) {
        sendPool.submit(() -> {
            byte[] bytes = NetworkManager.serializer(response);
            networkManager.sendData(new Record(bytes, address));
            logger.info("Отправлен ответ серверу!");
        });
    }
}
