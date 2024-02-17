package prog.lab4.world;

public enum DollInside {
    COTTON("вата"),
    SAWDUST("опилки");
    String type;

    DollInside(String type) {
        this.type = type;
    }

    public String Type() {
        return this.type;
    }
}
