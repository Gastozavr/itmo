import ru.ifmo.se.pokemon.*;
import pokemon.*;
public class Main {
    public static void main(String[] args) {
	Battle b = new Battle();
	Dratini p1 = new Dratini("Змейка",2);
	Linoone p2 = new Linoone("Летяга",3);
	Cresselia p3 = new Cresselia("Фея",1);
	Magnemite p4 = new Magnemite("Притягиваетль",2);
	Magneton p5 = new Magneton("Большая притягивалка",2);
	Claydol p6 = new Claydol("Инжир",3);
	b.addAlly(p1);
	b.addAlly(p3);
	b.addAlly(p5);
	b.addFoe(p2);
	b.addFoe(p4);
	b.addFoe(p6);
	b.go();
    }
}
