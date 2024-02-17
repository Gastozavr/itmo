import prog.lab4.*;
import prog.lab4.Moveable;
import prog.lab4.home.Room;
import prog.lab4.human.*;
import prog.lab4.human.Girl;
import prog.lab4.world.*;
import prog.lab4.world.Clother;
import prog.lab4.world.ItemsException;
import prog.lab4.world.Weariable;

public class Main {
    public static void main(String[] args) {

        Room home = new Room("комната");
        Room.Bed bed = home.new Bed("постель Незнайки");
        Boy Neznaika = new Boy("Синеглазка", new Eyes(Eyes.EyesColor.BLUE));
        Girl Sineglazka = new Girl("Синеглазка", new Eyes(Eyes.EyesColor.BLUE), new Jowls());
        Girl Other = new Girl("другая малышка", new Eyes(Eyes.EyesColor.GRAY), new Jowls(Jowls.JowlsTypes.PLUMP, Jowls.JowlsTypes.RUDDY));
        Other.setItems(Weariable.SMALLSUITCASE);
        Other.setClother(Clother.WHITEROBE, Clother.WHITEHAT);
        Moveable chair = new Moveable() {
            @Override
            public void moveToBed(Person who, Room.Bed arg) {
                System.out.println(who.getName() + " подвинул(а) к " + arg.getName() + " стул");
            }
        };

        System.out.println("\nInitialization module done\n");

        try {
            Neznaika.want(Boy.Wishes.BREAKDOLL);
            Neznaika.want(Boy.Wishes.INSIDEDOLL);
            Neznaika.throwItem("куклу");
            Neznaika.forgetThought("одежде");
            Neznaika.search(Weariable.KNIFE);
            Neznaika.seeReflection("в зеркале");
            Neznaika.makeFaces("перед зеркалом", "разглядывая свое лицо");
            System.out.println();
            Sounds.STEPS.makeSound("Тут за дверью послышались ");
            Neznaika.goToBed(Boy.MoveTypes.FAST);
            Neznaika.coverUp(CoverTypes.BLANKET);
            home.addPerson(Sineglazka);
            home.addPerson(Other);
            Other.printClother();
            Other.printItems();
            Other.getJowlsTypes();
            Other.see(Eyes.WatchTypes.HARD, Accesoires.HORNGLASSES);
            Neznaika.understand(Sineglazka, Other, "Медуница");
            System.out.println();
            chair.moveToBed(Other, bed);
            Other.put(Weariable.SMALLSUITCASE);
            Other.shakeHead();
            Other.say("");

        } catch (ItemsException ex) {
            System.out.println("ОШИБКА! " + ex.getMessage());
            Other.shakeHead();
            Other.say("");
        }
        catch (SayException ex){
            Other.say("какая-то реплика");
        }
    }
}

