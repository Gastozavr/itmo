package itmo.programming.lab5.utility;

/**
 * Интерфейс проверки правильности хранимых данных
 *
 * @author Andrew Schmunk
 * @version 1
 */
public interface Validatable {
    /**
     * @return true, если данные имеют правильный формат
     */
    boolean validate();
}
