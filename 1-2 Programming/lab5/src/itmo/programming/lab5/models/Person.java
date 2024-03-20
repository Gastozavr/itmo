package itmo.programming.lab5.models;

import itmo.programming.lab5.utility.Validatable;

import java.util.Objects;

/**
 * Класс человека
 */
public class Person implements Validatable {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private long height; //Значение поля должно быть больше 0
    private Color eyeColor; //Поле не может быть null
    private Country nationality; //Поле может быть null

    /**
     * Констркутор
     *
     * @param name        имя
     * @param height      рост
     * @param eyeColor    цвет глаз
     * @param nationality национальность
     */
    public Person(String name, long height, Color eyeColor, Country nationality) {
        this.name = name;
        this.height = height;
        this.eyeColor = eyeColor;
        this.nationality = nationality;
    }

    /**
     * конструктор
     *
     * @param data строка из котороый будет создан объект
     */
    public Person(String data) {
        try {
            this.name = data.split(" ; ")[0];
            try {
                this.height = (data.split(" ; ")[1].equals("null") ? null : Long.parseLong(data.split(" ; ")[1]));
            } catch (NumberFormatException e) {
                return;
            }
            ;
            try {
                this.eyeColor = Color.valueOf(data.split(" ; ")[2]);
            } catch (NullPointerException | IllegalArgumentException e) {
                this.eyeColor = null;
            }
            try {
                this.nationality = Country.valueOf(data.split(" ; ")[3]);
            } catch (NullPointerException | IllegalArgumentException e) {
                this.eyeColor = null;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }

    /**
     * @return возвращает имя человека
     */
    public String getName() {
        return name;
    }

    /**
     * @param name устанавливаемое имя
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return возвращает рост
     */
    public long getHeight() {
        return height;
    }

    /**
     * @param height устанавливаемый рост
     */
    public void setHeight(long height) {
        this.height = height;
    }

    /**
     * @return возврашает цвет глаз
     */
    public Color getEyeColor() {
        return eyeColor;
    }

    /**
     * @param eyeColor устанавливаемый цвет глаз
     */
    public void setEyeColor(Color eyeColor) {
        this.eyeColor = eyeColor;
    }

    /**
     * @return возвращает национальность
     */
    public Country getNationality() {
        return nationality;
    }

    /**
     * @param nationality устанавливаемая национальность
     */
    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    /**
     * Функция проверки на валидность полей объекта
     *
     * @return true, если поля валидны
     */
    @Override
    public boolean validate() {
        if (name == null || name.isEmpty()) return false;
        if (height <= 0) return false;
        return eyeColor != null;
    }

    /**
     * @return возвращает объект, переведенный в строковое представление
     */
    @Override
    public String toString() {
        return name + " ; " + height + " ; " + eyeColor + " ; " + (nationality == null ? "null" : nationality);
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
        Person person = (Person) obj;
        return name.equals(person.name) && height == person.height && eyeColor.equals(person.eyeColor) && nationality.equals(person.nationality);
    }

    /**
     * @return возвращает Хэш-код объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, height, eyeColor, nationality);
    }
}