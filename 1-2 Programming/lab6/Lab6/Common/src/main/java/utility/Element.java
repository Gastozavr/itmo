package utility;

import java.io.Serializable;

/**
 * Абстрактный класс элементов
 *
 * @author Andrew Schmunk
 * @version 1.0
 */
public abstract class Element implements Comparable<Element>, Validatable, Serializable {
    private static final long serialVersionUID = 11L;
    /**
     * Функция получения id
     *
     * @return возвращает id элемента
     */
    abstract public long getId();
}