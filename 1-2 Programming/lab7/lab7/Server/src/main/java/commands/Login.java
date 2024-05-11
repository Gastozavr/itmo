package commands;

import managers.DBManager;
import utility.ExecutionResponse;

public class Login extends Command {
    private final DBManager dbManager;

    public Login(DBManager dbManager) {
        super("login", "авторизация");
        this.dbManager = dbManager;
    }

    @Override
    public ExecutionResponse apply(String[] arguments,String login) {
        var arg = arguments[1].split(";");
        if (dbManager.exists(arg[0], arg[1])) {
            return new ExecutionResponse(true, "Вы успешно авторизованы!");
        } else return new ExecutionResponse(false, "Неверные логин или пароль!");
    }
}
