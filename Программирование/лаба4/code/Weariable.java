package prog.lab4.world;

public enum Weariable {
    NOTHING("ничего нет"),
    SMALLSUITCASE("небольшой коричневый чемоданчик"),
    KNIFE("нож");
    private String type;

    Weariable(String type) {
        this.type = type;
    }

    public String Type() {
        return this.type;
    }

}
