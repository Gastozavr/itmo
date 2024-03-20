package itmo.programming.lab5.managers;

import java.io.StringWriter;
import java.io.StringReader;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.lang.NullPointerException;
import java.util.*;

import itmo.programming.lab5.models.StudyGroup;
import itmo.programming.lab5.utility.Console;
import au.com.bytecode.opencsv.*;

/**
 * Класс файлового менеджера
 *
 * @author Andrew Schmunk
 * @version 1.0
 */
public class DumpManager {
    /**
     * название файла
     */
    private final String fileName;
    /**
     * консоль
     */
    private final Console console;

    /**
     * конструктор
     *
     * @param fileName название файла
     * @param console  консоль
     */

    public DumpManager(String fileName, Console console) {
        this.fileName = fileName;
        this.console = console;
    }

    /**
     * Функция сериализации коллекции
     *
     * @param collection преобразуемая коллекция
     * @return коллекция, преобразованная в строку
     */
    private String collection2CSV(Collection<StudyGroup> collection) {
        try {
            StringWriter sw = new StringWriter();
            CSVWriter csvWriter = new CSVWriter(sw, ';');
            for (var e : collection) {
                csvWriter.writeNext(StudyGroup.toArray(e));
            }
            String csv = sw.toString();
            return csv;
        } catch (Exception e) {
            console.printError("Ошибка сериализации");
            return null;
        }
    }

    /**
     * Функция записи коллекции в файл
     *
     * @param collection записываемая коллекция
     */
    public void writeCollection(Collection<StudyGroup> collection) {
        OutputStreamWriter writer = null;
        try {
            var csv = collection2CSV(collection);
            if (csv == null) return;
            writer = new OutputStreamWriter(new FileOutputStream(fileName));
            try {
                writer.write(csv);
                writer.flush();
                console.println("Коллекция успешна сохранена в файл!");
            } catch (IOException e) {
                console.printError("Неожиданная ошибка сохранения");
            }
        } catch (FileNotFoundException | NullPointerException e) {
            console.printError("Файл не найден");
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                console.printError("Ошибка закрытия файла");
            }
        }
    }

    /**
     * Функция десереализации коллекции
     *
     * @param s сериализованная коллекция
     * @return возвращает десереализованную коллекцию
     */
    private LinkedList<StudyGroup> CSV2collection(String s) {
        try {
            StringReader sr = new StringReader(s);
            CSVReader csvReader = new CSVReader(sr, ';');
            LinkedList<StudyGroup> ds = new LinkedList<StudyGroup>();
            String[] record = null;
            while ((record = csvReader.readNext()) != null) {
                StudyGroup d = StudyGroup.fromArray(record);
                if (d.validate())
                    ds.add(d);
                else
                    console.printError("Файл с колекцией содержит не действительные данные");
            }
            csvReader.close();
            return ds;
        } catch (Exception e) {
            console.printError("Ошибка десериализации");
            return null;
        }
    }

    /**
     * Функция чтения коллекции из файла
     *
     * @param collection коллекция, в которую будут записаны данные из файла
     */
    public void readCollection(Collection<StudyGroup> collection) {
        if (fileName != null && !fileName.isEmpty()) {
            try (var fileReader = new Scanner(new File(fileName))) {
                var s = new StringBuilder("");
                while (fileReader.hasNextLine()) {
                    s.append(fileReader.nextLine());
                    s.append("\n");
                }
                collection.clear();
                for (var e : CSV2collection(s.toString()))
                    collection.add(e);
                if (collection != null) {
                    console.println("Коллекция успешна загружена!");
                    return;
                } else
                    console.printError("В загрузочном файле не обнаружена необходимая коллекция!");
            } catch (FileNotFoundException exception) {
                console.printError("Загрузочный файл не найден!");
            } catch (IllegalStateException exception) {
                console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        } else {
            console.printError("Аргумент командной строки с загрузочным файлом не найден!");
        }
        collection = new LinkedHashSet<StudyGroup>();
    }
}