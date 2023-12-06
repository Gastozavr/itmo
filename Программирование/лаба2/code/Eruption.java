package attacks.special;
import ru.ifmo.se.pokemon.*;

public class Eruption extends SpecialMove{

	public Eruption(){
		super(Type.FIRE,150,100);
	}

	protected String describe(){
		return "использует атаку Eruption";
	}

	protected double calcBaseDamage(Pokemon att, Pokemon def){
		return 150*def.getHP()/def.getStat(Stat.HP);
	}

}
