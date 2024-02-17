package prog.lab3.human;

import prog.lab3.world.Clother;
import prog.lab3.world.Weariable;
import prog.lab3.world.Accesoires;

import java.util.List;

public abstract class Person {
    protected String name;
    public Eyes eyes;
    protected List<Weariable> items;
    protected List<Clother> clother;

    public Person(String name, Eyes eyes) {
        this.name = name;
        this.eyes = eyes;
    }

    public abstract void see(WatchTypes type, Accesoires from);

    public abstract String getName();

    public abstract void setName(String name);

    public abstract List<Weariable> getItems();

    public abstract void printItems();

    public abstract void setItems(Weariable... items);

    public abstract void addItems(Weariable... items);

    public abstract List<Clother> getClother();

    public abstract void printClother();

    public abstract void setClother(Clother... clother);

    public abstract void addClother(Clother... clother);
}


