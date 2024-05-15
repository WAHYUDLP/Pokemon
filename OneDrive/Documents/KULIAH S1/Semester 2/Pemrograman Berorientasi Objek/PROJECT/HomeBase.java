import java.util.ArrayList;
import java.util.List;

public class HomeBase {
    private List<Monster> storedMonsters;

    public HomeBase() {
        storedMonsters = new ArrayList<>();
    }

    public void storeMonster(Monster monster) {
        storedMonsters.add(monster);
    }

    public void retrieveMonster(Monster monster) {
        storedMonsters.remove(monster);
    }

    public void levelUpMonster(Monster monster) {
        monster.setLevel(monster.getLevel() + 1);
        System.out.println(monster.getNama() + " has leveled up to " + monster.getLevel());
    }

    public void evolveMonster(Monster monster) {
        // Implementasi evolusi, contoh sederhana
        List<Element> newElements = new ArrayList<>();
        newElements.add(new Element("EvolvedElement"));

        String newElement = "EvolvedElement";
        monster.setElement(newElements);

        System.out.println(monster.getNama() + " has evolved into " + newElement);
    }

    public void healMonster(Monster monster) {
        monster.setHealthPoint(100); // Misalnya sembuh ke full HP
        System.out.println(monster.getNama() + " has been fully healed.");
    }

    public void buyItem(Item item) {
        // Implementasi pembelian item
        System.out.println("Bought " + item.getNama());
    }

    public void enterHomeBase(PlayerMonster playerMonster) {
        System.out.println("Welcome back to Home Base!");
        // Lakukan tindakan seperti merefresh status monster, menyimpan data, dan
        // lainnya
        storeMonster(playerMonster);
        levelUpMonster(playerMonster);
        healMonster(playerMonster);
    }
}
