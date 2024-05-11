package commands;

import java.io.Serializable;

public enum CommandTypes implements Serializable {

    ADD("add"),
    ADDIFMIN("add_if_min"),
    AVERAGEOFSTUDENTSCOUNT("average_of_students_count"),
    CLEAR("clear"),
    EXIT("exit"),
    INFO("info"),
    MINBYGROUPADMIN("min_by_group_admin"),
    PRINTDESCENDING("print_descending"),
    REMOVEBYID("remove_by_id"),
    REMOVEGREATER("remove_greater"),
    REMOVELOWER("remove_lower"),
    SHOW("show"),
    UPDATE("update"),
    HELP("help"),
    REGISTER("register"),
    LOGIN("login"),
    EXECUTESCRIPT("execute_script");
    private String type;

    private CommandTypes(String type) {
        this.type = type;
    }

    public String Type() {
        return type;
    }

    private static final long serialVersionUID = 14L;

    public static CommandTypes getByString(String string) {
        try {

            return CommandTypes.valueOf(string.replace(Character.toString('_'), "").toUpperCase());
        } catch (NullPointerException | IllegalArgumentException e) {
            return null;
        }
    }
}
