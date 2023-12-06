package prog.lab3.human;

public enum WatchTypes {
    HARD("строго");
    private String type;

    WatchTypes(String type) {
        this.type = type;
    }

    public String Type() {
        return this.type;
    }
}

