package models;

import java.io.Serializable;

public enum Color implements Serializable {

    GREEN,
    RED,
    BLACK,
    YELLOW,
    ORANGE;
    private static final long serialVersionUID = 5L;
    /**
     * @return возвращает строку со всеми элементами enum
     * @author Andrew Schmunk
     */

    public static String names() {
        StringBuilder nameList = new StringBuilder();
        for (var colorType : values()) {
            nameList.append(colorType.name()).append(", ");
        }
        return nameList.substring(0, nameList.length()-2);
    }




}