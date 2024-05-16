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
        monsters.add(new WildMonster("Aqua Spirit", 4, List.of(new Element("AIR")), true));

        monsters.add(new WildMonster("Drake", 5, List.of(new Element("API")), false));
    }

 public void explore(PlayerMonster playerMonster) {
        if (monsters.isEmpty()) {
            System.out.println("No monsters left to encounter in the dungeon.");
            return;
        }

        int encounterChance = random.nextInt(100);  // Random chance generation
        if (encounterChance < 50) {  // 50% chance to encounter a monster
            Monster wildMonster = getMonster();
            System.out.println("A wild " + wildMonster.getNama() + " appears!");
            battleArena.startBattle(playerMonster, wildMonster);
        } else {
            System.out.println("No monsters encountered this time.");
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
