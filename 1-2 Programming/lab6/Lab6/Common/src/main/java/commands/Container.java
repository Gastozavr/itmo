package commands;

import java.io.Serializable;

public class Container implements Serializable {
    private static final long serialVersionUID = 15L;
    private CommandTypes commandType;
    private String args;

    public Container(CommandTypes commandType, String args) {
        this.commandType = commandType;
        this.args = args;
    }

    public CommandTypes getCommandType() {
        return commandType;
    }

    public String getArgs() {
        return args;
    }
}
