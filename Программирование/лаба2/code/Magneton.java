package pokemon;
import ru.ifmo.se.pokemon.*;
import attacks.status.*;
import attacks.special.*;

public class Magneton extends Magnemite{
	public Magneton(){
		this("Безымянный",1);
	}

	public Magneton(String name, int level){
		super(name,level);
		this.setType(Type.ELECTRIC, Type.STEEL);
		this.setStats(50,60,95,120,70,70);
		this.addMove(new RazorWind());
	}
}
