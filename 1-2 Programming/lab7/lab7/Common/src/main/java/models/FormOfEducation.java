package models;

import java.io.Serializable;

public enum FormOfEducation implements Serializable {
    DISTANCE_EDUCATION,
    FULL_TIME_EDUCATION,
    EVENING_CLASSES;
    private static final long serialVersionUID = 8L;
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