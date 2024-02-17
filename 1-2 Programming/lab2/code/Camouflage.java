package attacks.status;
import ru.ifmo.se.pokemon.*;

public class Camouflage extends StatusMove{
	public Camouflage(){
		super(Type.NORMAL,0,0);
	}

	protected String describe(){
		return "использует атаку Camouflage";
	}
}
