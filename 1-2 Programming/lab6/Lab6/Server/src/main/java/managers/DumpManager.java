package managers;

import java.io.*;
import java.lang.NullPointerException;
import java.util.*;
import models.StudyGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.Console;
import com.opencsv.*;

/**
 * Класс файлового менеджера
 *
 * @author Andrew Schmunk
 * @version 1.0
 */
public class DumpManager implements Serializable {
    public static final Logger logger = LoggerFactory.getLogger(DumpManager.class);
    private static final long serialVersionUID = 4L;
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
            CSVWriter csvWriter = new CSVWriter(sw);
            for (var e : collection) {
                csvWriter.writeNext(StudyGroup.toArray(e));
            }
            String csv = sw.toString();
            return csv;
        } catch (Exception e) {
            logger.error("Ошибка сериализации");
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
                logger.info("Коллекция успешна сохранена в файл!");
            } catch (IOException e) {
                logger.error("Неожиданная ошибка сохранения");
            }
        } catch (FileNotFoundException | NullPointerException e) {
            logger.error("Файл не найден");
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                logger.error("Ошибка закрытия файла");
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
            CSVReader csvReader = new CSVReader(sr);
            LinkedList<StudyGroup> ds = new LinkedList<StudyGroup>();
            String[] record = null;
            while ((record = csvReader.readNext()) != null) {
                if (record.length==8) {
                    StudyGroup d = StudyGroup.fromArray(record);
                    if (d.validate())
                        ds.add(d);
                }
            }
            csvReader.close();
            return ds;
        } catch (Exception e) {
            logger.error("Ошибка десериализации");
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
                    logger.info("Коллекция успешна загружена!");
                    return;
                } else
                    logger.error("В загрузочном файле не обнаружена необходимая коллекция!");
            } catch (FileNotFoundException exception) {
                logger.error("Загрузочный файл не найден!");
            } catch (IllegalStateException exception) {
                logger.error("Непредвиденная ошибка!");
                System.exit(0);
            }
        } else {
            logger.error("Аргумент командной строки с загрузочным файлом не найден!");
        }
        collection = new LinkedHashSet<StudyGroup>();
    }
}