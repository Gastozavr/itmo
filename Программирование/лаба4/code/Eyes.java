package prog.lab4.human;


import prog.lab4.world.Accesoires;

import java.util.Objects;

public class Eyes {
    private EyesColor color;

    public Eyes() {
        this.color = EyesColor.BROWN;
    }

    public Eyes(EyesColor color) {
        this.color = color;
    }

    public enum EyesColor {
        BROWN("карие"),
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


    protected void watch(WatchTypes type, Accesoires from) {
        System.out.println(this.toString() + " " + type.Type() + " смотрели из-за " + from.Type());
    }


    @Override
    public String toString() {
        return color.Color() + " глазки";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Eyes girl = (Eyes) o;
        return (Objects.equals(this.color, girl.color));
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
