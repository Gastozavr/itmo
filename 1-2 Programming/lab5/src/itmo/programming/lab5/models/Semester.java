package itmo.programming.lab5.models;

public enum Semester {
    THIRD,
    SIXTH,
    SEVENTH;

    /**
     * @return возвращает строку со всеми элементами enum
     * @author Andrew Schmunk
     */
    public static String names() {
        StringBuilder nameList = new StringBuilder();
        for (var colorType : values()) {
            nameList.append(colorType.name()).append(", ");
        }
        return nameList.substring(0, nameList.length() - 2);
    }
}