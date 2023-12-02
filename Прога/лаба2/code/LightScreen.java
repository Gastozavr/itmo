package attacks.status;
import ru.ifmo.se.pokemon.*;


public class LightScreen extends StatusMove{
	public LightScreen() {
		super(Type.PSYCHIC,0,0);
	}
	protected String describe(){
		return "использует атаку Light Screen";
	}
}
