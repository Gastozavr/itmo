package prog.lab4;

import prog.lab4.home.Room;
import prog.lab4.human.Person;

public interface Moveable {
    void moveToBed(Person who, Room.Bed arg);
}
