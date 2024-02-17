package prog.lab3;

public enum MoveTypes {
    FAST("быстро");
    private String type;

    MoveTypes(String type) {
        this.type = type;
    }

    public String Type() {
        return this.type;
    }

}

