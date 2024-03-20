package itmo.programming.lab5.commands;

/**
 * Интерфейс, содержащий методы для получения названия команды и ее описания
 *
 * @author Andrew Schmunk
 * @version 1.0
 */
public interface Describable {
    /**
     * Функция для получения названия
     *
     * @return возвращает название
     */
    String getName();

    /**
     * Функция для получения описания
     *
     * @return возвращает описание
     */
    String getDescription();
}
