package commands;

import managers.DBManager;
import utility.ExecutionResponse;

public class Register extends Command {
    private final DBManager dbManager;

    public Register(DBManager dbManager) {
        super("register", "Регистрация");
        this.dbManager = dbManager;
    }

    @Override
    public ExecutionResponse apply(String[] arguments,String login) {
        var arg = arguments[1].split(";");
        if (dbManager.registerUser(arg[0], arg[1])) {
            return new ExecutionResponse(true, "Вы успешно зарегистрированы!");
        } else return new ExecutionResponse(false, "Такой пользователь уже существует!");
    }
}
