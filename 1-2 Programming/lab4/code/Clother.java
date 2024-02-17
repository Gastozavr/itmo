package prog.lab4.world;

public enum Clother {
    NOTHING("ни во что"),
    WHITEROBE("белый халат"),
    WHITEHAT("белая шапочка");
    private String type;

    Clother(String type) {
        this.type = type;
    }

    public String Type() {
        return this.type;
    }
}
