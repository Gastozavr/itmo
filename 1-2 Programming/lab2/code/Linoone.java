package pokemon;
import ru.ifmo.se.pokemon.*;
import attacks.special.*;
import attacks.physical.*;
import attacks.status.*;
public class Linoone extends Zigzagoon{
	public Linoone(){
		this("Безымянный",1);
	}
	public Linoone(String name,int level){
		super(name,level);
		this.setStats(78,70,61,50,61,100);
		this.setType(Type.NORMAL);
		this.setMove(new ShadowPunch(), new ThunderWave(), new HydroPump());

	}
}
