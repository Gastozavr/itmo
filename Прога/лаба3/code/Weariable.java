package prog.lab3.world;

public enum Weariable {
    NOTHING("ничего нет"),
    SMALLSUITCASE("небольшой коричневый чемоданчик");
    private String type;

    Weariable(String type) {
        this.type = type;
    }

    public String Type() {
        return this.type;
    }

}
