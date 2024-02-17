package attacks.status;
import ru.ifmo.se.pokemon.*;

public class ThunderWave extends StatusMove{
	public ThunderWave(){
		super(Type.ELECTRIC,0,90);
	}

	protected String describe(){
		return "Использует Thunder Wave";
	}
	
	protected void applyOppEffects(Pokemon p){
		p.setCondition(new Effect().condition(Status.PARALYZE));
	}
}
