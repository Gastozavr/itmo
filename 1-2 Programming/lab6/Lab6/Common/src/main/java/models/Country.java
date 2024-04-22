package models;

import java.io.Serializable;

public enum Country implements Serializable {
    UNITED_KINGDOM,
    SPAIN,
    JAPAN;
    private static final long serialVersionUID = 7L;
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