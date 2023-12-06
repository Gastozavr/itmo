package pokemon;
import ru.ifmo.se.pokemon.*;
import attacks.status.*;
import attacks.special.*;

public class Claydol extends Baltoy{
	public Claydol(){
		this("Безымянный",1);
	}

	public Claydol(String name,int level){
		super(name,level);
		this.setType(Type.GROUND, Type.PSYCHIC);
		this.setStats(60,70,105,70,120,75);
		this.setMove(new LightScreen(), new ThunderWave(), new RazorWind(), new Thunderbolt());
	}
}
