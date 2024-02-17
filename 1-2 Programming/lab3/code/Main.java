import prog.lab3.human.*;
import prog.lab3.*;
import prog.lab3.human.Girl;
import prog.lab3.world.*;
import prog.lab3.home.Room;
import prog.lab3.world.Clother;

public class Main {
    public static void main(String[] args) {
        Room home = new Room("комната");
        Boy Neznaika = new Boy("Незнайка", new Eyes(EyesColor.GRAY));
        Girl Sineglazka = new Girl("Синеглазка", new Eyes(EyesColor.BLUE), new Jowls());
        Girl Other = new Girl("другая малышка", new Eyes(EyesColor.GRAY), new Jowls(JowlsTypes.PLUMP, JowlsTypes.RUDDY));
        Other.setItems(Weariable.SMALLSUITCASE);
        Other.setClother(Clother.WHITEROBE, Clother.WHITEHAT);
        System.out.println("\nInitialization module done\n");
        Sounds.STEPS.say("Тут за дверью послышались ");
        Neznaika.goToBed(MoveTypes.FAST);
        Neznaika.coverUp(CoverTypes.BLANKET);
        home.addPerson(Sineglazka);
        home.addPerson(Other);
        Other.printClother();
        Other.printItems();
        Other.getJowlsTypes();
        Other.see(WatchTypes.HARD, Accesoires.HORNGLASSES);
        Neznaika.think(Sineglazka, Other, "Медуница");
    }
}

