package prog.lab3.world;

public enum Accesoires {
    HORNGLASSES("круглых роговых очков");
    private String type;

    Accesoires(String name) {
        this.type = name;
    }

    public String Type() {
        return this.type;
    }
}

