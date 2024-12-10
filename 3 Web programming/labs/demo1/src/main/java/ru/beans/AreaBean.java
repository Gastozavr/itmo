package beans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import managers.DBManager;
import models.Point;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Named("bean")
@SessionScoped
public class AreaBean implements Serializable {
    private static final Logger log = Logger.getLogger(AreaBean.class.getName());
    private int r; // Радиус
    private int x; // Координата X
    private double y; // Координата Y
    private boolean isHit; // Попадание в область
    private List<Point> points; // Список точек, которые сохраняются
    private List<Integer> xValues = Arrays.asList(-5, -4, -3, -2, -1, 0, 1, 2, 3);
    private List<Integer> rValues = Arrays.asList(1, 2, 3, 4, 5);
    @Inject
    private DBManager dbManager;

    // Геттеры и сеттеры
    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public List<Point> getPoints() {
        return points;
    }

    public List<Integer> getXValues() {
        return xValues;
    }

    public List<Integer> getRValues() {
        return rValues;
    }

    // Setter for xValues
    public void setXValues(List<Integer> xValues) {
        this.xValues = xValues;
    }
    public void setRValues(List<Integer> rValues) {
        this.rValues = rValues;
    }

    // Инициализация значений
    @PostConstruct
    public void init() {
        x = 0;  // Начальная координата X
        y = 5;  // Начальная координата Y
        r = 3;  // Начальный радиус
        if (points == null) {
            points = new ArrayList<>(); // Инициализация списка точек
        }
        points = dbManager.getAllPoints();

    }

    // Метод для проверки попадания в область
    private boolean check() {
        boolean part1 = (x >= 0 && x <= r) && (y >= 0 && y <= r / 2);
        boolean part2 = (y >= 0) && (x <= 0 && x <= -r / 2) && (y <= 2 * x + r);
        boolean part3 = (x * x + y * y <= (r / 2) * (r / 2)) && (y <= 0) && (x <= 0);
        return part1 || part2 || part3;
    }

    // Метод для обработки отправки данных
    public void submit() {
        log.info("submited1");
        isHit = check(); // Проверка попадания
        Point point = new Point(x, y, r, isHit); // Создание точки
        points.add(point); // Добавление точки в список
        dbManager.addPoint(point);
        log.info("submited");
    }

    // Метод для очистки списка точек
    public void clear() {
        points.clear(); // Очищаем список точек
        dbManager.clearTable();
    }

}