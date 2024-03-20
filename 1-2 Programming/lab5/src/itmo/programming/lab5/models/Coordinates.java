package itmo.programming.lab5.models;

import itmo.programming.lab5.utility.Validatable;

import java.util.Objects;

/**
 * класс координат
 */
public class Coordinates implements Validatable {
    private Long x; //Значение поля должно быть больше -638, Поле не может быть null
    private Integer y; //Поле не может быть null

    /**
     * Конструктор
     *
     * @param x координата х
     * @param y координата у
     */
    public Coordinates(Long x, Integer y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Конструктор
     *
     * @param data строка, по которой будет создан объект
     */
    public Coordinates(String data) {
        try {
            try {
                this.x = Long.parseLong(data.split(";")[0]);
            } catch (NumberFormatException e) {
            }
            try {
                this.y = Integer.parseInt(data.split(";")[1]);
            } catch (NumberFormatException e) {
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }

    /**
     * Функция проверки валидности полей объекта
     *
     * @return true, если поля объекта валидны
     */
    @Override
    public boolean validate() {
        if (x <= -638 || x == null) return false;
        return y != null;
    }

    /**
     * @return возвращает координату х
     */
    public Long getX() {
        return x;
    }

    /**
     * @return возвращает координату у
     */
    public Integer getY() {
        return y;
    }

    /**
     * @param x устанавливаемая координата х
     */
    public void setX(long x) {
        this.x = x;
    }

    /**
     * @param y устанавливаемая координата у
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return возвращает объект, переведенный в строковое представление
     */
    @Override
    public String toString() {
        return x + ";" + y;
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
        Coordinates coordinates = (Coordinates) obj;
        return x.equals(coordinates.x) && y.equals(coordinates.y);
    }

    /**
     * @return возвращает Хэш-код объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}