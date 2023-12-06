package prog.lab4.world;
import prog.lab4.Soundable;
public enum Sounds implements Soundable {
    STEPS("шаги");
    private String type;

    Sounds(String type) {
        this.type = type;
    }

    public String Type() {
        return this.type;
    }

    public void makeSound(String prefix) {
        System.out.println(prefix + this.Type());
    }
}
