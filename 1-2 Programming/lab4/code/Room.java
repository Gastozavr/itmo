package prog.lab4.home;

import prog.lab4.human.Person;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Room {
    private List<Person> peopleInside;
    private String name;

    public Room() {
        this.name = "комната";
        this.peopleInside = new LinkedList();
        Stat.counter++;
        System.out.println("Комната комната создана");
    }

    public Room(String name) {
        this.name = name;
        this.peopleInside = new LinkedList();
        Stat.counter++;
        System.out.println("Комната " + this.toString() + " создана");
    }

    public class Bed {
        private String name;

        public Bed() {
            this.name = "кровать";
            System.out.println("Кровать кровать создана");
        }

        public Bed(String name) {
            this.name = name;
            System.out.println("Кровать " + this.name + " создана");
        }

        public void SetName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

    public static class Stat {
        private static int counter = 0;

        public static int getCounter() {
            return counter;
        }

    }


    public void setPerson(Person... person) {
        this.peopleInside.clear();
        if (person.length != 0) {
            Person[] arr = person;
            int var1 = person.length;
            for (int i = 0; i < var1; i++) {
                Person var2 = arr[i];
                this.peopleInside.add(var2);
            }
        }
    }

    public void addPerson(Person person) {
        if (!this.peopleInside.contains(person)) {
            this.peopleInside.add(person);
            System.out.println("в " + this.toString() + " вошел(ла) " + person.getName());
        }
    }

    public void removePerson(Person person) {
        this.peopleInside.remove(person);
        System.out.println(person.getName() + " вышел(ла) из " + this.toString());
    }

    public List<Person> getPerson() {
        return this.peopleInside;
    }


    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return (Objects.equals(this.name, room.name) && Objects.equals(this.peopleInside, room.peopleInside));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, peopleInside);
    }
}

