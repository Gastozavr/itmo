package commands;

import java.io.Serializable;

public enum CommandTypes implements Serializable {

    Add("add"),
    AddIfMin("add_if_min"),
    AverageStudentsCount("average_of_students_count"),
    Clear("clear"),
    Exit("exit"),
    Info("info"),
    MinByGroupAdmin("min_by_group_admin"),
    PrintDescending("print_descending"),
    RemoveById("remove_by_id"),
    RemoveGreater("remove_greater"),
    RemoveLower("remove_lower"),
    Save("save"),
    Show("show"),
    Update("update"),
    Help("help");
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

            return CommandTypes.valueOf(string.toUpperCase().charAt(0) + string.substring(1).toLowerCase());
        } catch (NullPointerException | IllegalArgumentException e) {
        }
        return null;
    }
}
