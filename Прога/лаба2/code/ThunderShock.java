package attacks.special;
import ru.ifmo.se.pokemon.*;

public class ThunderShock extends SpecialMove{
	public ThunderShock(){
		super (Type.ELECTRIC,40,100);
	}
	
	protected String describe(){
		return "использует атаку Thunder Shock";
	}

	protected void applyOppEffects(Pokemon p){
		p.setCondition(new Effect().chance(0.1).condition(Status.PARALYZE));
	}	
}
