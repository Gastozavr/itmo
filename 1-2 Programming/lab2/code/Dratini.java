package pokemon;
import ru.ifmo.se.pokemon.*;
import attacks.status.*;
import attacks.special.*;
public class Dratini extends Pokemon {
	public Dratini(){
		this("Безымянный",1);
	}

	public Dratini(String name, int level){
		super(name,level);
		this.setType(Type.DRAGON);
		this.setStats(41,64,45,50,50,50);
		this.setMove(new LightScreen(), new ThunderShock(),new ThunderWave());
	}
}

