package prog.lab3.home;

import prog.lab3.human.Person;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Room {
    private List<Person> peopleInside;
    private String name;

    public Room() {
        this.name = "комната";
        this.peopleInside = new LinkedList();
        System.out.println("Комната комната создана");
    }

    public Room(String name) {
        this.name = name;
        this.peopleInside = new LinkedList();
        System.out.println("Комната " + this.toString() + " создана");
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

