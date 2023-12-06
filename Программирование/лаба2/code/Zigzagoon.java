package pokemon;
import ru.ifmo.se.pokemon.*;

public class Zigzagoon extends Pokemon{
	public Zigzagoon(){
		this("Безымянный",1);
	}


	public Zigzagoon (String name, int level){
		super(name,level);
		this.setType(Type.NORMAL);
		this.setStats(38,30,41,30,41,60);
	}
}
