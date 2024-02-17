package attacks.special;
import ru.ifmo.se.pokemon.*;

public class RazorWind extends SpecialMove{
	public RazorWind(){
		super(Type.NORMAL,80,100);
	}

	protected String describe(){
		return "использует атаку Razor Wind";
	}

	protected double calcCriticalHit(Pokemon pokemon, Pokemon pokemon1) {
        	if (1./8. > Math.random()) {
            		System.out.println("Критический удар!");
            		return 2.0;
        	}
	       	else {
            		return 1.0;
       		}
	}
}

