package itmo.programming.lab5.managers;

import itmo.programming.lab5.models.*;
import itmo.programming.lab5.utility.Console;

import java.util.NoSuchElementException;
import java.time.LocalDate;

/**
 * Класс менеджера  по запросам данных из командной строки
 *
 * @author Andrew Schmunk
 * @version 1.0
 */
public class AskManager {
    /**
     * исключение для выхода из цикла опроса
     */
    public static class AskBreak extends Exception {
    }

    /**
     * Функция опроса учебной группы
     *
     * @param console консоль
     * @param id      уникальный номер учебной группы
     * @return возвращает учебную группу
     * @throws AskBreak исключение, возникающее при принудительном выходе из опроса
     */
    public static StudyGroup askStudyGroup(Console console, Long id) throws AskBreak {
        try {
            String name;
            while (true) {
                console.print("name: ");
                name = console.readln().trim();
                if (name.equals("exit")) throw new AskBreak();
                if (!name.isEmpty()) break;
            }
            var coordinates = askCoordinates(console);
            console.print("studentsCount: ");
            Integer studentsCount;

            while (true) {
                var line = console.readln().trim();
                if (line.equals("exit")) throw new AskBreak();
                if (!line.isEmpty()) {
                    try {
                        studentsCount = Integer.parseInt(line);
                        if (studentsCount > 0) break;
                    } catch (NumberFormatException e) {
                    }
                }
                console.print("studentsCount: ");
            }
            var formOfEducation = askFormOfEducation(console);
            var semester = askSemester(console);
            var admin = askAdmin(console);
            return new StudyGroup(id, name, coordinates, LocalDate.now(), studentsCount, formOfEducation, semester, admin);
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }

    /**
     * Функция опроса координат
     *
     * @param console консоль
     * @return возвращает координаты
     * @throws AskBreak исключение, возникающее при принудительном выходе из опроса
     */
    public static Coordinates askCoordinates(Console console) throws AskBreak {
        try {
            long x;
            while (true) {
                console.print("coordinates.x: ");
                var line = console.readln().trim();
                if (line.equals("exit")) throw new AskBreak();
                if (!line.equals("")) {
                    try {
                        x = Long.parseLong(line);
                        if (x > -638) break;
                    } catch (NumberFormatException e) {
                    }
                }
            }
            int y;
            while (true) {
                console.print("coordinates.y: ");
                var line = console.readln().trim();
                if (line.equals("exit")) throw new AskBreak();
                if (!line.equals("")) {
                    try {
                        y = Integer.parseInt(line);
                        break;
                    } catch (NumberFormatException e) {
                    }
                }
            }
            return new Coordinates(x, y);
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }

    /**
     * Функция опроса формы обучения
     *
     * @param console консоль
     * @return возвращает форму обучения
     * @throws AskBreak исключение, возникающее при принудительном выходе из опроса
     */
    public static FormOfEducation askFormOfEducation(Console console) throws AskBreak {
        try {
            console.print("FormOfEducation (" + FormOfEducation.names() + "): ");
            FormOfEducation formOfEducation;
            while (true) {
                var line = console.readln().trim().toUpperCase();
                if (line.equalsIgnoreCase("exit")) throw new AskBreak();
                if (!line.isEmpty()) {
                    try {
                        formOfEducation = FormOfEducation.valueOf(line);
                        break;
                    } catch (NullPointerException | IllegalArgumentException e) {
                    }
                }
                console.print("FormOfEducation: ");
            }
            return formOfEducation;
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }

    /**
     * Функция опроса семестра
     *
     * @param console консоль
     * @return возвращает семестр
     * @throws AskBreak исключение, возникающее при принудительном выходе из опроса
     */
    public static Semester askSemester(Console console) throws AskBreak {
        try {
            console.print("Semester (" + Semester.names() + "): ");
            Semester semester;
            while (true) {
                var line = console.readln().trim().toUpperCase();
                if (line.equalsIgnoreCase("exit")) throw new AskBreak();
                if (!line.equals("")) {
                    try {
                        semester = Semester.valueOf(line);
                        break;
                    } catch (NullPointerException | IllegalArgumentException e) {
                    }
                }
                console.print("Color: ");
            }
            return semester;
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }

    /**
     * Функция опроса админа группы
     *
     * @param console консоль
     * @return возвращает админа группы
     * @throws AskBreak исключение, возникающее при принудительном выходе из опроса
     */
    public static Person askAdmin(Console console) throws AskBreak {
        try {
            String name;

            while (true) {
                console.print("admin.name: ");
                name = console.readln().trim();
                if (name.equals("exit")) throw new AskBreak();
                if (!name.isEmpty()) break;
            }

            console.print("admin.height: ");
            Integer height;
            while (true) {
                var line = console.readln().trim();
                if (line.equals("exit")) throw new AskBreak();
                if (line.equals("")) {
                    height = null;
                    break;
                }
                try {
                    height = Integer.parseInt(line);
                    if (height > 0) break;
                } catch (NumberFormatException e) {
                }
                console.print("person.weight: ");
            }

            console.print("admin.eyeColor (" + Color.names() + "): ");
            Color eyeColor;
            while (true) {
                var line = console.readln().trim().toUpperCase();
                if (line.equals("exit")) throw new AskBreak();
                if (!line.equals("")) {
                    try {
                        eyeColor = Color.valueOf(line);
                        break;
                    } catch (NullPointerException | IllegalArgumentException e) {
                    }
                }
                console.print("admin.eyeColor: ");
            }

            console.print("admin.nationality (" + Country.names() + "): ");
            Country nationality;
            while (true) {
                var line = console.readln().trim().toUpperCase();
                if (line.equals("exit")) throw new AskBreak();
                if (line.equals("")) {
                    nationality = null;
                    break;
                }
                try {
                    nationality = Country.valueOf(line);
                    break;
                } catch (NullPointerException | IllegalArgumentException e) {
                }
                console.print("admin.nationality : ");
            }
            return new Person(name, height, eyeColor, nationality);
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }
}

