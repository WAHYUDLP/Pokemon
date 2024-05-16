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
         // Add the existing monsters
    monsters.add(new WildMonster("Aqua Spirit", 4, List.of(new Element("AIR")), true));
    monsters.add(new WildMonster("Drake", 5, List.of(new Element("API")), false));

    // Add 30 additional monsters
    monsters.add(new WildMonster("Terra Beast", 3, List.of(new Element("TANAH")), false));
    monsters.add(new WildMonster("Flame Phoenix", 6, List.of(new Element("API")), true));
    monsters.add(new WildMonster("Frost Wyvern", 7, List.of(new Element("ES")), true));
    monsters.add(new WildMonster("Thunderbird", 5, List.of(new Element("ANGIN")), true));
    monsters.add(new WildMonster("Spectral Ghost", 4, List.of(new Element("API")), false));
    monsters.add(new WildMonster("Rock Golem", 4, List.of(new Element("TANAH")), false));
    monsters.add(new WildMonster("Ocean Leviathan", 8, List.of(new Element("AIR")), true));
    monsters.add(new WildMonster("Inferno Demon", 9, List.of(new Element("API")), true));
    monsters.add(new WildMonster("Blizzard Yeti", 7, List.of(new Element("ES")), true));
    monsters.add(new WildMonster("Storm Elemental", 6, List.of(new Element("ANGIN")), true));
    monsters.add(new WildMonster("Dark Shadow", 5, List.of(new Element("API")), false));
    monsters.add(new WildMonster("Magma Serpent", 6, List.of(new Element("TANAH")), true));
    monsters.add(new WildMonster("Deep Sea Kraken", 8, List.of(new Element("AIR")), true));
    monsters.add(new WildMonster("Hellfire Salamander", 10, List.of(new Element("API")), true));
    monsters.add(new WildMonster("Frozen Wraith", 9, List.of(new Element("ES")), true));
    monsters.add(new WildMonster("Sky Dragon", 7, List.of(new Element("ANGIN")), true));
    monsters.add(new WildMonster("Phantom Specter", 6, List.of(new Element("API")), false));
    monsters.add(new WildMonster("Iron Golem", 5, List.of(new Element("TANAH")), false));
    monsters.add(new WildMonster("Marine Leviathan", 9, List.of(new Element("AIR")), true));
    monsters.add(new WildMonster("Infernal Demon", 11, List.of(new Element("API")), true));
    monsters.add(new WildMonster("Frostbite Yeti", 8, List.of(new Element("ES")), true));
    monsters.add(new WildMonster("Tempest Elemental", 7, List.of(new Element("ANGIN")), true));
    monsters.add(new WildMonster("Nightmare Shade", 6, List.of(new Element("API")), false));
    monsters.add(new WildMonster("Lava Serpent", 7, List.of(new Element("TANAH")), true));
    monsters.add(new WildMonster("Abyssal Kraken", 10, List.of(new Element("AIR")), true));
    monsters.add(new WildMonster("Scorching Salamander", 12, List.of(new Element("API")), true));
    monsters.add(new WildMonster("Glacial Wraith", 10, List.of(new Element("ES")), true));
    monsters.add(new WildMonster("Gale Dragon", 8, List.of(new Element("ANGIN")), true));
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
