package prog.lab3.world;

public enum Sounds implements prog.lab3.Soundable {
    STEPS("шаги");
    private String type;

    Sounds(String type) {
        this.type = type;
    }

    public String Type() {
        return this.type;
    }

    public void say(String prefix) {
        System.out.println(prefix + this.Type());
    }
}
