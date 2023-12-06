package prog.lab3.human;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Jowls {
    private List<JowlsTypes> type;

    public Jowls() {
        this.type = new LinkedList();
        this.type.add(JowlsTypes.DEFAULT);
    }

    public Jowls(JowlsTypes... type) {
        this.type = new LinkedList();
        if (type.length == 0) {
            this.type.add(JowlsTypes.DEFAULT);
        } else {
            JowlsTypes[] arr = type;
            int var1 = type.length;
            for (int i = 0; i < var1; i++) {
                JowlsTypes var2 = arr[i];
                this.type.add(var2);
            }
        }
    }

    public List<JowlsTypes> getType() {
        return this.type;
    }

    public void setType(JowlsTypes... type) {
        this.type.clear();
        if (type.length == 0) {
            this.type.add(JowlsTypes.DEFAULT);
        } else {
            JowlsTypes[] arr = type;
            int var1 = arr.length;

            for (int i = 0; i < var1; i++) {
                JowlsTypes var2 = arr[i];
                this.type.add(var2);
            }
        }
    }

    public void addType(JowlsTypes... type) {
        if (this.type.size() == 1 & this.type.get(0) == JowlsTypes.DEFAULT) {
            this.type.clear();
        }
        JowlsTypes[] arr = type;
        int var1 = type.length;
        for (int i = 0; i < var1; i++) {
            JowlsTypes var2 = arr[i];
            this.type.add(var2);
        }
    }


    @Override
    public String toString() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jowls jowls = (Jowls) o;
        return (Objects.equals(this.type, jowls.type));
    }


    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}

