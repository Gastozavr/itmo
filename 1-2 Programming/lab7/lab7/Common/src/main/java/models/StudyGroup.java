package models;

import utility.Element;
import utility.Validatable;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * класс учебной группы
 */
public class StudyGroup extends Element implements Validatable {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int studentsCount; //Значение поля должно быть больше 0
    private FormOfEducation formOfEducation; //Поле не может быть null
    private Semester semesterEnum; //Поле не может быть null
    private String owner;
    private Person groupAdmin; //Поле не может быть null

    /**
     * констркутор
     *
     * @param id              уникальный номер объекта
     * @param name            название
     * @param coordinates     координаты
     * @param creationDate    дата создания
     * @param studentsCount   количество студентов в группе
     * @param formOfEducation форма обучения
     * @param semesterEnum    семестр
     * @param groupAdmin      админ группы
     */
    public StudyGroup(Long id, String name, Coordinates coordinates, LocalDate creationDate, int studentsCount, FormOfEducation formOfEducation, Semester semesterEnum, String owner, Person groupAdmin) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.studentsCount = studentsCount;
        this.formOfEducation = formOfEducation;
        this.semesterEnum = semesterEnum;
        this.owner = owner;
        this.groupAdmin = groupAdmin;
    }

    /**
     * Конструктор
     *
     * @param data строка, из которой будет создан объект
     * @return возвращает учебную группу
     */
    public static StudyGroup fromArray(String[] data) {
        Long id;
        String name;
        Coordinates coordinates;
        LocalDate creationDate;
        int studentsCount;
        FormOfEducation formOfEducation;
        Semester semesterEnum;
        String owner;
        Person groupAdmin;
        try {
            try {
                id = Long.parseLong(data[0]);
            } catch (NumberFormatException e) {
                id = null;
            }
            name = data[1];
            coordinates = new Coordinates(data[2]);
            try {
                creationDate = LocalDate.parse(data[3], DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (DateTimeParseException e) {
                creationDate = null;
            }
            try {
                studentsCount = Integer.parseInt(data[4]);
            } catch (NumberFormatException e) {
                studentsCount = 0;
            }
            try {
                formOfEducation = FormOfEducation.valueOf(data[5]);
            } catch (NullPointerException | IllegalArgumentException e) {
                formOfEducation = null;
            }
            try {
                semesterEnum = Semester.valueOf(data[6]);
            } catch (NullPointerException | IllegalArgumentException e) {
                semesterEnum = null;
            }
            owner = data[7];
            groupAdmin = (data[8].equals("null") ? null : new Person(data[8]));
            return new StudyGroup(id, name, coordinates, creationDate, studentsCount, formOfEducation, semesterEnum, owner, groupAdmin);
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        return null;
    }

    public String toStr() {
        var str = "";
        str += Long.toString(this.id);
        str += "/";
        str += this.name;
        str += "/";
        str += this.coordinates.toString();
        str += '/';
        str += this.creationDate.toString();
        str += "/";
        str += Integer.toString(this.studentsCount);
        str += "/";
        str += this.formOfEducation.toString();
        str += "/";
        str += this.semesterEnum.toString();
        str += "/";
        str += this.owner;
        str += "/";
        str += this.groupAdmin.toString();
        return str;
    }

    /**
     * Преобразует объект в строчный массив
     *
     * @param e объект
     * @return возвращает массив строк
     */
    public static String[] toArray(StudyGroup e) {
        var list = new ArrayList<String>();
        list.add(Long.toString(e.getId()));
        list.add(e.getName());
        list.add(e.getCoordinates().toString());
        list.add(e.getCreationDate().toString());
        list.add(Integer.toString(e.getStudentsCount()));
        list.add(e.getFormOfEducation().toString());
        list.add(e.getSemester().toString());
        list.add(e.getGroupAdmin().toString());
        return list.toArray(new String[0]);
    }

    /**
     * @return возвращает id
     */
    @Override
    public long getId() {
        return id;
    }

    /**
     * @return возвращает название
     */
    public String getName() {
        return name;
    }

    /**
     * @return возвращает координаты
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * @return возвращает дату создания
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * @return возвращает количество студентов в группе
     */
    public int getStudentsCount() {
        return studentsCount;
    }

    /**
     * @return возвращает форму обучения
     */
    public FormOfEducation getFormOfEducation() {
        return formOfEducation;
    }

    /**
     * @return возврашает семстр
     */
    public Semester getSemester() {
        return semesterEnum;
    }

    /**
     * @return возвращает админа группы
     */
    public Person getGroupAdmin() {
        return groupAdmin;
    }

    /**
     * @param o the object to be compared.
     * @return возвращает на сколько один число студентов в одной группе больше, чем в другой
     */

    @Override
    public int compareTo(Element o) {
        return this.studentsCount - ((StudyGroup) o).studentsCount;
    }

    /**
     * Проверяет валидность полей объекта
     *
     * @return true, если поля объекта валидны
     */
    @Override
    public boolean validate() {
        if (id == null || id <= 0) return false;
        if (name == null || name.isEmpty()) return false;
        if (coordinates == null | !coordinates.validate()) return false;
        if (creationDate == null) return false;
        if (studentsCount <= 0) return false;
        if (formOfEducation == null) return false;
        if (semesterEnum == null) return false;
        return groupAdmin != null;
    }

    public String getOwner() {
        return owner;
    }

    /**
     * @return возвращает объект, переведенный в строковое представление
     */
    @Override
    public String toString() {
        return "StudyGroup{\"id\": " + id + ", " +
                "\"name\": \"" + name + "\", " +
                "\"coordinates\": \"" + coordinates + "\", " +
                "\"creationDate\" = \"" + creationDate + "\", " +
                "\"studentsCount\": \"" + studentsCount + "\", " +
                "\"formOfEducation\" = \"" + formOfEducation + "\", " +
                "\"type\": \"" + semesterEnum + "\", " +
                "\"Admin\": \"" + groupAdmin + "\"" + "}";
    }

    /**
     * Переопределение метода эквивалентности объект
     *
     * @param obj сравниваемый объект
     * @return true, если объекты эквивалентны
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        StudyGroup studyGroup = (StudyGroup) obj;
        return id.equals(studyGroup.id);
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return возвращает Хэш-код объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, studentsCount, formOfEducation, semesterEnum, groupAdmin);
    }
}