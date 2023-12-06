package prog.lab3.human;

public enum JowlsTypes {
    DEFAULT("обычные"),
    PLUMP("пухлые"),
    RUDDY("румяные");
    private String type;

    JowlsTypes(String type) {
        this.type = type;
    }

    public String Type() {
        return this.type;
    }
}
