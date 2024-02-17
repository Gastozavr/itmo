package prog.lab3.human;

import prog.lab3.human.Person;
import prog.lab3.world.Weariable;
import prog.lab3.world.Clother;
import prog.lab3.MoveTypes;
import prog.lab3.world.CoverTypes;
import prog.lab3.human.Jowls;
import prog.lab3.Thinkable;
import prog.lab3.human.WatchTypes;
import prog.lab3.world.Accesoires;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;



public class Boy extends Person implements Thinkable {
    public Boy(){
        super("Безымянный", new Eyes());
        this.items = new LinkedList();
        this.clother = new LinkedList();
        this.clother.add(Clother.NOTHING);
        this.items.add(Weariable.NOTHING);
        System.out.println("Безымянный создан");
    }
    public Boy(String name){
        super(name, new Eyes());
        this.items = new LinkedList();
        this.clother = new LinkedList();
        this.clother.add(Clother.NOTHING);
        this.items.add(Weariable.NOTHING);
        System.out.println(this.toString() + " создан");
    }

    public Boy(Eyes eyes){
        super("Безымянный", eyes);
        this.items = new LinkedList();
        this.clother = new LinkedList();
        this.clother.add(Clother.NOTHING);
        this.items.add(Weariable.NOTHING);
        System.out.println("Безымянный создан");
    }
    public Boy(String name, Eyes eyes) {
        super(name, eyes);
        this.items = new LinkedList();
        this.clother = new LinkedList();
        this.clother.add(Clother.NOTHING);
        this.items.add(Weariable.NOTHING);
        System.out.println(this.toString() + " создан");
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
        String answer = this.toString() + " одет в ";
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

    @Override
    public void think(Person p1, Person p2, String name) {
        p2.setName(name);
        String answer = this.getName();
        answer += " понял(а), что это и есть ";
        answer += p2.getName();
        answer += ", о котом(ой) ему(ей) говорил(а) ";
        answer += p1.getName();
        System.out.println(answer);
    }

    public void goToBed(MoveTypes parametres) {
        System.out.println(this.name + " " + parametres.Type() + " юркнул в постель");
    }

    public void coverUp(CoverTypes object) {
        System.out.println(this.name + " накрылся " + object.Type());
    }


    @Override
    public String toString(){
        return this.name;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Boy boy = (Boy) o;
        return (Objects.equals(this.name, boy.name) && Objects.equals(this.eyes, boy.eyes) && Objects.equals(this.items, boy.items) && Objects.equals(this.clother, boy.clother));
    }


    @Override
    public int hashCode() {
        return Objects.hash(name,eyes, items, clother);
    }



}

