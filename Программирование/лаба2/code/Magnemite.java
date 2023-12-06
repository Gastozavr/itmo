package pokemon;
import ru.ifmo.se.pokemon.*;
import attacks.status.*;

public class Magnemite extends Pokemon{
	public Magnemite(){
		this("Безымянный",1);
	}

	public Magnemite(String name,int level){
		super(name,level);
		this.setType(Type.ELECTRIC, Type.STEEL);
		this.setStats(25,35,70,95,55,45);
		this.setMove(new LightScreen(), new ThunderWave());
	}
}
