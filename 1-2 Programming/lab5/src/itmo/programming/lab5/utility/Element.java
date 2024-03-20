package itmo.programming.lab5.utility;

/**
 * Абстрактный класс элементов
 *
 * @author Andrew Schmunk
 * @version 1.0
 */
public abstract class Element implements Comparable<Element>, Validatable {
    /**
     * Функция получения id
     *
     * @return возвращает id элемента
     */
    abstract public long getId();
}