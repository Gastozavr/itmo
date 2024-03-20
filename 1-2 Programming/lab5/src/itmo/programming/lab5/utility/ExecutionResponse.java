package itmo.programming.lab5.utility;

/**
 * Класс ответа о выполнении
 *
 * @author Andrew Schmunk
 * @version 1.0
 */
public class ExecutionResponse {
    /**
     * код завершения
     */
    private boolean exitCode;
    /**
     * сообщение
     */
    private String message;

    /**
     * Конструктор
     *
     * @param code код завершения
     * @param s    сообщение
     */

    public ExecutionResponse(boolean code, String s) {
        exitCode = code;
        message = s;
    }

    /**
     * Конструктор
     *
     * @param s сообщение
     */
    public ExecutionResponse(String s) {
        this(true, s);
    }

    /**
     * Функция получения кода завершения
     *
     * @return возвращает код завершения
     */
    public boolean getExitCode() {
        return exitCode;
    }

    /**
     * Функция получения сообщения
     *
     * @return возвращает сообщение
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return возвращает объект, переведенный в строковое представление
     */
    public String toString() {
        return String.valueOf(exitCode) + ";" + message;
    }
}