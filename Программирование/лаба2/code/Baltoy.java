package pokemon;
import ru.ifmo.se.pokemon.*;

public class Baltoy extends Pokemon{
	public Baltoy(){
		this("Безымянный",1);
	}
	public Baltoy(String name,int level){
		 super(name,level);
		 this.setType(Type.GROUND, Type.PSYCHIC);
		 this.setStats(40,40,55,40,70,55);
	 }
}
