package prog.lab3.world;

public enum CoverTypes {
    BLANKET("одеялом");
    private String type;

    CoverTypes(String type) {
        this.type = type;
    }

    public String Type() {
        return this.type;
    }

}
