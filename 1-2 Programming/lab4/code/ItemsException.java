package prog.lab4.world;

import prog.lab4.human.Person;

public class ItemsException extends Exception {
    private Person person;

    public Person getPerson() {
        return this.person;
    }

    public ItemsException(String message) {
        super(message);
    }
}
