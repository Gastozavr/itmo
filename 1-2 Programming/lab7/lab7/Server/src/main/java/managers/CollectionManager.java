package managers;

import models.Person;
import models.StudyGroup;
import utility.Sortable;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

import java.util.concurrent.ConcurrentSkipListMap;

import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

/**
 * Класс менеджера коллекции
 *
 * @author Andrew Schmunk
 * @version 1.0
 */
public class CollectionManager implements Sortable, Serializable {
    private static final long serialVersionUID = 2L;
    /**
     * переменная для выдачи следующего id
     */
    private long nextId = 1;
    /**
     * словарь для хранения элементов коллекции по id
     */
    /**
     * словарь для хранения элементов коллекции по id
     */
    private ConcurrentSkipListMap<Long, StudyGroup> groups = new ConcurrentSkipListMap();
    /**
     * хранимая коллекция
     */
    private CopyOnWriteArraySet<StudyGroup> collection = new CopyOnWriteArraySet <>();
    /**
     * Время последней инициализации менеджера
     */
    private LocalDateTime lastInitTime;
    /**
     * вреся последнего сохранения коллекции
     */
    private LocalDateTime lastSaveTime;
    /**
     * файловый менеджер
     */


    private final DBManager dbManager;

    public CollectionManager(DBManager dbManager) {
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.dbManager = dbManager;
    }

    /**
     * @return возвращает время последней инициализации менеджера
     */
    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }

    /**
     * @return возвращает время последнего сохранения коллекции
     */
    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }



    /**
     * Функция загрузки коллекции из файла в менеджер
     *
     * @return true, если загрузка прошла успешно
     */
    public boolean loadCollection() {
        groups.clear();
        dbManager.loadCollection(collection);
        lastInitTime = LocalDateTime.now();
        for (StudyGroup e : collection)
            if (getById(e.getId()) != null) {
                collection.clear();
                return false;
            } else {
                if (e.getId() > nextId) nextId = e.getId();
                groups.put(e.getId(), e);
            }
        sort();
        return true;
    }

    /**
     * Функция для проверки наличия объекта в коллекции
     *
     * @param e проверяемый объект
     * @return true, если объект присутствует в коллекции
     */
    public boolean isContain(StudyGroup e) {
        return getById(e.getId()) != null;
    }

    /**
     * Функция получения коллекции из менеджера
     *
     * @return возвращает хранимую в менеджере коллекцию
     */
    public CopyOnWriteArraySet  <StudyGroup> getCollection() {
        return this.collection;
    }

    /**
     * Функция получения свободного id
     *
     * @return возвращает свобожный id
     */
    public Long getFreeId() {
        while (getById(nextId) != null) {
            if (++nextId < 0) nextId = 1;
        }
        return nextId;
    }

    /**
     * Функиця для получения объекта из коллекции по id
     *
     * @param id уникальный номер объекта
     * @return возвращает объект, хранимый в коллекции
     */
    public StudyGroup getById(Long id) {
        return groups.get(id);
    }

    /**
     * Функция добавления элемента в коллекцию
     *
     * @param e добавляемый объект
     * @return true, если объект успешно добавлен
     */
    public boolean add(StudyGroup e) {
        if (isContain(e)) return false;
        groups.put(e.getId(), e);
        collection.add(e);
        sort();
        return true;
    }

    /**
     * Функция для удаления элемента коллекции по id
     *
     * @param id уникальный номер элемента
     * @return true, если элемент успешно удален
     */
    public void remove(long id) {
        StudyGroup element = getById(id);
        groups.remove(element.getId());
        collection.remove(element);
        sort();
    }

    /**
     * Функция для получения первого элемента в коллекции
     *
     * @return возвращает первый элемент коллекци
     */
    public StudyGroup getFirst() {
        return collection.stream().limit(1).collect(Collectors.toList()).get(0);
    }

    public List<StudyGroup> getAll() {
        return collection.stream().sorted((StudyGroup b, StudyGroup a) -> b.getName().compareTo(a.getName())).collect(Collectors.toList());
    }

    /**
     * Функция сортировки коллекции
     */
    @Override
    public void sort() {
        collection = new CopyOnWriteArraySet <StudyGroup>(collection.stream().
                sorted().collect(Collectors.toList()));
    }

    /**
     * @return возвращает объект, переведенный в строковое представление
     */
    @Override
    public String toString() {
        if (collection.isEmpty()) return "Коллекция пуста!";
        System.out.println(collection.size());
        return collection.stream().map(Objects::toString).collect(Collectors.joining ("\n\n"));
    }
}
