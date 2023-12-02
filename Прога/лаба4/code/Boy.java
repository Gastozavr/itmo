package prog.lab4.human;

import prog.lab4.Understandable;
import prog.lab4.world.*;
import prog.lab4.world.DollInside;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Random;


public class Boy extends Person implements Understandable {
    Reflection reflection;
    public Boy(){
        super("Безымянный", new Eyes());
        reflection = new Reflection("свое отражение");
        this.items = new LinkedList();
        this.clother = new LinkedList();
        this.clother.add(Clother.NOTHING);
        this.items.add(Weariable.NOTHING);
        Stat.counter++;
        System.out.println("Безымянный создан");
    }
    public Boy(String name){
        super(name, new Eyes());
        reflection = new Reflection("свое отражение");
        this.items = new LinkedList();
        this.clother = new LinkedList();
        this.clother.add(Clother.NOTHING);
        this.items.add(Weariable.NOTHING);
        Stat.counter++;
        System.out.println(this.name + " создан");
    }

    public Boy(Eyes eyes){
        super("Безымянный", eyes);
        reflection = new Reflection("свое отражение");
        this.items = new LinkedList();
        this.clother = new LinkedList();
        this.clother.add(Clother.NOTHING);
        this.items.add(Weariable.NOTHING);
        Stat.counter++;
        System.out.println("Безымянный создан");
    }
    public Boy(String name, Eyes eyes) {
        super(name, eyes);
        reflection = new Reflection("свое отражение");
        this.items = new LinkedList();
        this.clother = new LinkedList();
        this.clother.add(Clother.NOTHING);
        this.items.add(Weariable.NOTHING);
        Stat.counter++;
        System.out.println(this.toString() + " создан");
    }

    public static class Stat {
        private static int counter = 0;

        public static int getCounter() {
            return counter;
        }

    }

    class Reflection {
        String name;

        Reflection(String name) {
            this.name = name;
        }

        Reflection() {
            this.name = "отражение";
        }


        void setName(String name) {
            this.name = name;
        }

        String getName() {
            return this.name;
        }
    }


    public void seeReflection(String where) {
        System.out.println(this.name + " увидел " + this.reflection.getName()+" "+where);
    }

    public enum Wishes {
        BREAKDOLL("разломить куклу"),
        INSIDEDOLL("посмотреть, что внутри куклы ");
        String type;

        Wishes(String type) {
            this.type = type;
        }

        public String Type() {
            return this.type;
        }
    }


    public enum MoveTypes {
        FAST("быстро");
        private String type;

        MoveTypes(String type) {
            this.type = type;
        }

        public String Type() {
            return this.type;
        }

    }


    public void put(Weariable object) throws ItemsException {
        if (this.items.size() == 1 && this.items.get(0) == Weariable.NOTHING) {
            throw new ItemsException("У " + this.name + " нет такого предмета");
        }
        if (object == Weariable.NOTHING) {
            throw new ItemsException(this.name + " пытается поставить ничего");
        }
        System.out.println(this.name + " поставил " + object.Type());

    }

    public void want(Wishes wish) {
        String message = this.name + " захотел " + wish.Type();
        if (wish == Wishes.INSIDEDOLL) {
            for (int i = 0; i < DollInside.values().length; i++) {
                message += DollInside.values()[i].Type();
                if (i != DollInside.values().length - 1) {
                    message += " или ";
                }
            }

        }
        System.out.println(message);
    }

    public void throwItem(String item) {
        System.out.println(this.name + " бросил " + item + " на пол");
    }

    public void makeFaces(String place,String subAction){
        System.out.println(this.name+ " стал корчить " + place +" гримассы "+subAction);
    }
    public void search(Weariable item){
        System.out.println(this.name+" принялся искать "+ item.Type());
    }

    public void forgetThought(String thought){
        System.out.println(this.name+" забыл об "+ thought);
    }
    @Override
    public void see(Eyes.WatchTypes type, Accesoires from) {
        this.eyes.watch(type, from);
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
    public void understand(Person p1, Person p2, String name) {
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
    public String toString() {
        return this.getName();
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
        return Objects.hash(name, eyes, items, clother);
    }

}

