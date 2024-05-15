import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dungeon {
    private List<Monster> monsters;
    private Random random;
    private BattleArena battleArena;

    public Dungeon(BattleArena battleArena) {
        this.monsters = new ArrayList<>();
        this.random = new Random();
        this.battleArena = battleArena; // Tambahkan inisialisasi BattleArena
        // Asal beberapa monster
        generateMonster();
    }

    // Metode lain di Dungeon


    private void generateMonster() {
        monsters.add(new WildMonster("Drake", 5, List.of(new Element("FIRE")), true));
        monsters.add(new WildMonster("Aqua Spirit", 4, List.of(new Element("WATER")), true));
    }

    public void explore(PlayerMonster playerMonster) {
        int randomChance = random.nextInt(100); // Generate a random number between 0 and 99
        if (randomChance < 50 && !monsters.isEmpty()) { // Adjust the chance as needed
            battleArena.startBattle(playerMonster, getMonster()); // Memanggil startBattle di BattleArena
        } else {
            System.out.println("No monsters found in this area.");
        }
    }
    

    void startBattle(Player player) {
        Monster wildMonster = getMonster();
        if (wildMonster != null) {
            System.out.println("A wild " + wildMonster.getNama() + " appears!");
            player.battleMonster(wildMonster);
            
        } else {
            System.out.println("No more monsters in the dungeon.");
        }
    }

    private Monster getMonster() {
        if (!monsters.isEmpty()) {
            return monsters.remove(0);
        }
        return null;
    }
}
