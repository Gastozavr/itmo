package prog.lab3.human;

import prog.lab3.world.Clother;
import prog.lab3.world.Weariable;
import prog.lab3.human.WatchTypes;
import prog.lab3.world.Accesoires;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Girl extends Person {

    private Jowls jowls;

    public Girl() {
        super("Безымянная", new Eyes());
        this.items = new LinkedList();
        this.clother = new LinkedList();
        this.jowls = new Jowls();
        this.clother.add(Clother.NOTHING);
        this.items.add(Weariable.NOTHING);
        System.out.println("Безымянная создана");
    }

    public Girl(String name) {
        super(name, new Eyes());
        this.items = new LinkedList();
        this.clother = new LinkedList();
        this.jowls = new Jowls();
        this.clother.add(Clother.NOTHING);
        this.items.add(Weariable.NOTHING);
        System.out.println(this.toString() + " создана");
    }

    public Girl(Eyes eyes) {
        super("Безымянная", eyes);
        this.items = new LinkedList();
        this.clother = new LinkedList();
        this.jowls = new Jowls();
        this.clother.add(Clother.NOTHING);
        this.items.add(Weariable.NOTHING);
        System.out.println("Безымянная создана");
    }

    public Girl(Jowls jowls) {
        super("Безымянная", new Eyes());
        this.items = new LinkedList();
        this.clother = new LinkedList();
        this.jowls = jowls;
        this.clother.add(Clother.NOTHING);
        this.items.add(Weariable.NOTHING);
        System.out.println("Безымянная создана");
    }

    public Girl(String name, Eyes eyes) {
        super(name, eyes);
        this.items = new LinkedList();
        this.clother = new LinkedList();
        this.jowls = new Jowls();
        this.clother.add(Clother.NOTHING);
        this.items.add(Weariable.NOTHING);
        System.out.println(this.toString() + " создана");
    }

    public Girl(String name, Jowls jowls) {
        super(name, new Eyes());
        this.items = new LinkedList();
        this.clother = new LinkedList();
        this.jowls = jowls;
        this.clother.add(Clother.NOTHING);
        this.items.add(Weariable.NOTHING);
        System.out.println(this.toString() + " создана");
    }

    public Girl(Eyes eyes, Jowls jowls) {
        super("Безымянная", eyes);
        this.items = new LinkedList();
        this.clother = new LinkedList();
        this.jowls = jowls;
        this.clother.add(Clother.NOTHING);
        this.items.add(Weariable.NOTHING);
        System.out.println("Безымянная создана");
    }


    public Girl(String name, Eyes eyes, Jowls jowls) {
        super(name, eyes);
        this.items = new LinkedList();
        this.clother = new LinkedList();
        this.jowls = jowls;
        this.clother.add(Clother.NOTHING);
        this.items.add(Weariable.NOTHING);
        System.out.println(this.toString() + " создана");
    }


    @Override
    public void see(WatchTypes type, Accesoires from) {
        this.eyes.watch(type,from);
    }
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<Weariable> getItems() {
        return this.items;
    }


    @Override
    public void printItems() {
        String answer = "у " + this.toString() + " в руках ";
        for (int i = 0; i < this.items.size(); i++) {
            answer += this.items.get(i).Type();
            answer += " ";
        }
        System.out.println(answer);
    }

    @Override
    public void setItems(Weariable... items) {
        this.items.clear();
        if (items.length == 0) {
            this.items.add(Weariable.NOTHING);
        } else {
            Weariable[] arr = items;
            int var1 = arr.length;

            for (int i = 0; i < var1; i++) {
                Weariable var2 = arr[i];
                this.items.add(var2);
            }
        }
    }

    @Override
    public void addItems(Weariable... items) {
        if (this.items.size() == 1 & this.items.get(0) == Weariable.NOTHING) {
            this.items.clear();
        }
        Weariable[] arr = items;
        int var1 = items.length;
        for (int i = 0; i < var1; i++) {
            Weariable var2 = arr[i];
            this.items.add(var2);
        }
    }

    @Override
    public List<Clother> getClother() {
        return this.clother;
    }

    @Override
    public void printClother() {
        String answer = this.toString() + " одета в ";
        for (int i = 0; i < this.clother.size(); i++) {
            answer += this.clother.get(i).Type();
            answer += this.clother.size() - 1 != i ? " и " : " ";
        }
        System.out.println(answer);
    }


    @Override
    public void setClother(Clother... clother) {
        this.clother.clear();
        if (clother.length == 0) {
            this.clother.add(Clother.NOTHING);
        } else {
            Clother[] arr = clother;
            int var1 = arr.length;

            for (int i = 0; i < var1; i++) {
                Clother var2 = arr[i];
                this.clother.add(var2);
            }
        }
    }

    @Override
    public void addClother(Clother... clother) {
        if (this.clother.size() == 1 & this.clother.get(0) == Clother.NOTHING) {
            this.clother.clear();
        }
        Clother[] arr = clother;
        int var1 = clother.length;
        for (int i = 0; i < var1; i++) {
            Clother var2 = clother[i];
            this.clother.add(var2);
        }
    }

    public List<JowlsTypes> getJowlsTypes() {
        return this.jowls.getType();
    }

    public void setJowlsTypes(JowlsTypes... types) {
        this.jowls.setType(types);
    }

    public void printJowlsTypes() {
        String answer = "у " + this.toString() + " были ";
        for (int i = 0; i < this.jowls.getType().size(); i++) {
            answer += this.jowls.getType().get(i).Type();
            answer += " ";
        }
        answer += "щечки";
        System.out.println(answer);
    }


    @Override
    public String toString() {
        return this.getName();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Girl girl = (Girl) o;
        return (Objects.equals(this.name, girl.name) && Objects.equals(this.eyes, girl.eyes) && Objects.equals(this.items, girl.items) && Objects.equals(this.clother, girl.clother) && Objects.equals(this.jowls, girl.jowls));
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, eyes, jowls, items, clother);
    }
}
