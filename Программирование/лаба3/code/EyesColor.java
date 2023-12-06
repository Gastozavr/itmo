package prog.lab3.human;

public enum EyesColor {
    BROWN("коричневые"),
    GRAY("серые"),
    BLUE("голубые");
    private String color;

    EyesColor(String color) {
        this.color = color;
    }

    public String Color() {
        return this.color;
    }
}
