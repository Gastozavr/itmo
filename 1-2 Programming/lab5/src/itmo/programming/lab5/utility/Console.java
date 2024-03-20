package itmo.programming.lab5.utility;

import java.util.Scanner;

/**
 * Консоль для ввода команд и вывода результата
 *
 * @author Andrew Schmunk
 * @version 1.0
 */
public interface Console {
    void print(Object obj);

    void println(Object obj);

    String readln();

    boolean isCanReadln();

    void printError(Object obj);

    void printTable(Object obj1, Object obj2);

    void prompt();

    String getPrompt();

    void selectFileScanner(Scanner obj);

    void selectConsoleScanner();
}
