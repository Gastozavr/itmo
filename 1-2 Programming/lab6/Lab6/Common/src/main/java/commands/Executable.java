package commands;

import utility.ExecutionResponse;

/**
 * Интерфейс, предназначенный для исполнения
 *
 * @author Andrew Schmunk
 * @version 1.0
 */
public interface Executable  {
    /**
     * Функция для исполнение
     *
     * @param arguments массив с аргументами
     * @return возвращает ответ о выполнении команды
     */
    ExecutionResponse apply(String[] arguments);
}