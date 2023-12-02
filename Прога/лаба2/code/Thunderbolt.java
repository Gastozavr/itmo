package attacks.special;
import ru.ifmo.se.pokemon.*;

public class Thunderbolt extends SpecialMove{
	public Thunderbolt(){
		super(Type.ELECTRIC,90,100);
	}

	protected String describe(){
		 return "использует атаку Thunderbolt";
	}
	 
	protected void applyOppEffects(Pokemon p) {
		 p.setCondition(new Effect().chance(0.1).condition(Status.PARALYZE));
	}

}
