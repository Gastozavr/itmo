package prog.lab3.human;

import prog.lab3.world.Accesoires;

import java.util.Objects;

public class Eyes {
    private EyesColor color;

    public Eyes() {
        this.color = EyesColor.BROWN;
    }

    public Eyes(EyesColor color) {
        this.color = color;
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
