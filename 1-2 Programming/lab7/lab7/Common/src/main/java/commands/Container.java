package commands;

import java.io.Serializable;

public class Container implements Serializable {
    private static final long serialVersionUID = 15L;
    private String login;
    private String password;
    private CommandTypes commandType;
    private String args;

    public Container(String login, String password, CommandTypes commandType, String args) {
        this.commandType = commandType;
        this.args = args;
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public CommandTypes getCommandType() {
        return commandType;
    }

    public String getArgs() {
        return args;
    }
}
