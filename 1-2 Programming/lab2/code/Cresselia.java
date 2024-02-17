package pokemon;
import ru.ifmo.se.pokemon.*;
import attacks.special.*;
import attacks.status.*;
import attacks.physical.*;

public class Cresselia extends Pokemon{

	public Cresselia(){
		this("Безымянный",1);
	}

	public Cresselia(String name,int level){
		super(name,level);
		this.setType(Type.PSYCHIC);
		this.setStats(120,70,110,75,120,85);
		this.setMove(new ShadowPunch(), new ThunderWave(), new HydroPump(), new Eruption()); 
	}

}
