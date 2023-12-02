package prog.lab4.world;

public enum Accesoires {
    HORNGLASSES("круглых роговых очков");
    private final String type;

    Accesoires(String name) {
        this.type = name;
    }

    public String Type() {
        return this.type;
    }
}

