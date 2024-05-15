import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayerMonster extends Monster {

    private Player owner;

 
    public PlayerMonster(String nama, int level, List<Element> elements, Player owners) {
        super(nama, level, elements);
        this.owner = owners;
    }

     // Getter for name
     public String getNama() {
        return super.getNama();
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }

    @Override
    public void basicAttack(Monster target) {
        int damage = level * 10; // Contoh: serangan dasar mengurangi 10 HP per level

        // Reduksi HP target
        target.healthPoint -= damage;

        System.out.println(nama + " melakukan serangan dasar ke " + target.nama);
        System.out.println("Serangan mengurangi " + damage + " HP pada " + target.nama);
        System.out.println(target.nama + " sekarang memiliki " + target.healthPoint + " HP");
    }

    @Override
    public void specialAttack(Monster target) {
        // Determine the base damage of special attack
        int baseDamage = level * 10; // Adjust this calculation as needed

        // Calculate the actual damage with a random variation
        int actualDamage = (int) (baseDamage * (Math.random() + 0.5)); // Random damage between 50% to 100% of base
                                                                       // damage

        // Check if the attack misses (rare chance)
        boolean miss = Math.random() < 0.1; // 10% chance of missing

        if (miss) {
            System.out.println("Special attack missed!");
        } else {
            // Deduct HP from the target based on actual damage
            target.healthPoint -= actualDamage;
            System.out.println("Special attack hits! Target loses " + actualDamage + " HP.");

            // Sacrifice a percentage of own HP (e.g., 20%)
            int sacrificeHP = (int) (healthPoint * 0.2); // 20% sacrifice
            healthPoint -= sacrificeHP;
            System.out.println("You sacrificed " + sacrificeHP + " HP.");
        }
    }

    @Override

    public void elementalAttack(Monster target) {
        // Check if both attacking monster and target have valid elements
        if (element != null && target.element != null) {
            // Determine effectiveness based on elements
            int effectiveness = determineElementalEffectiveness(target.element);

            // Calculate base damage for elemental attack
            int baseDamage = level * 15; // Adjust this calculation as needed

            // Calculate actual damage with effectiveness multiplier
            int actualDamage = baseDamage * effectiveness;

            // Deduct HP from the target based on actual damage
            target.healthPoint -= actualDamage;

            System.out.println("Elemental attack hits! Target loses " + actualDamage + " HP.");
        } else {
            System.out.println("Cannot perform elemental attack. Invalid elements.");
        }
    }

    private int determineElementalEffectiveness(List<Element> elements) {
        for (Element element : elements) {
            switch (element.getNama().toLowerCase()) {
                case "air":
                    return element.getNama().equalsIgnoreCase("tanah") ? 2 : 1;
                case "tanah":
                    return element.getNama().equalsIgnoreCase("air") ? 2 : 1;
                case "api":
                    return element.getNama().equalsIgnoreCase("es") ? 2 : 1;
                case "es":
                    return element.getNama().equalsIgnoreCase("angin") ? 2 : 1;
                case "angin":
                    return element.getNama().equalsIgnoreCase("tanah") ? 2 : 1;
                default:
                    return 1; // Default effectiveness if elements don't match
            }
        }
        return 1; // Default effectiveness if no matching element is found
    }
    

    @Override
    public void useItem(Item item, Monster target) {
        Scanner scanner = new Scanner(System.in);

        if ("ElementalPotion".equals(item.getNama())) {
            System.out.println("Choose an element to apply: FIRE, WATER, EARTH, AIR, ICE");
            String input = scanner.nextLine().toUpperCase(); // Ambil input dan ubah ke huruf besar

            switch (input) {
                case "FIRE":
                case "WATER":
                case "EARTH":
                case "AIR":
                case "ICE":
                    List<Element> elementList = new ArrayList<>();
                    elementList.add(new Element(input)); // Buat objek Element sesuai pilihan pengguna
                    // Set element baru ke target
                    target.setElement(elementList);

                    int baseDamage = level * 10; // Example base damage
                    int actualDamage = baseDamage;

                    // Kurangi HP target berdasarkan actual damage
                    target.setHealthPoint(target.getHealthPoint() - actualDamage);

                    System.out.println("Used elemental potion. Target's element changed to " + input + ".");
                    System.out.println("Target loses " + actualDamage + " HP.");
                    break;
                default:
                    System.out.println("Invalid element choice. Potion has no effect.");
                    break;
            }
        } else {
            System.out.println("Cannot use this item. Invalid item type.");
        }

        scanner.close(); // Jangan lupa untuk menutup scanner setelah penggunaan
    }

    @Override
    public boolean flee() {
        // Calculate chance of successfully fleeing (e.g., 30% chance)
        boolean success = Math.random() < 0.3; // 30% chance of success

        if (success) {
            System.out.println("Successfully fled from the battle.");
            // Implement logic to move to Dungeon and exit battle arena (assuming Dungeon
            // and BattleArena classes exist)
            
        } else {
            System.out.println("Failed to flee. The battle continues.");
        }

        return success;
    }

    @Override

    public boolean isFainted() {
        return healthPoint <= 0;
    }

    @Override
    public void gainExperiencePoints(int points) {
        expPoint += points;
    }

    @Override
    public void evolve(Element newElement) {
        // Misalnya, kita ingin mengganti elemen pertama dalam List<Element>
        if (!element.isEmpty()) {
            element.set(0, newElement);
            System.out.println(getNama() + " has evolved. New element: " + newElement);
        } else {
            System.out.println("Cannot evolve. Element list is empty.");
        }
    }
}
    
