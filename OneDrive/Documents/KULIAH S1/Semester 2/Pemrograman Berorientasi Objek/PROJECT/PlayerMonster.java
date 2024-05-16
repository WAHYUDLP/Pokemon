import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayerMonster extends Monster {

    private Player owner;
    private int wins;
    private boolean hasEvolved;

    public PlayerMonster(String nama, int level, List<Element> elements, Player owners) {
        super(nama, level, elements);
        this.owner = owners;
        this.wins = 0;
        this.hasEvolved = false;
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

    public int getWins() {
        return wins;
    }

    public void incrementWins() {
        this.wins++;
    }

    public boolean hasEvolved() {
        return hasEvolved;
    }

    public void setHasEvolved(boolean hasEvolved) {
        this.hasEvolved = hasEvolved;
    }

    @Override
    public void basicAttack(Monster target) {
        int damage = 12; // Contoh: serangan dasar mengurangi 10 HP per level

        // Reduksi HP target
        target.healthPoint -= damage;

        System.out.println("SIAAAAAA!!!!!!......");
        System.out.println(nama + " melakukan serangan dasar ke " + target.nama);
        System.out.println("Serangan mengurangi " + damage + " HP pada " + target.nama);
        System.out.println(target.nama + " sekarang memiliki " + target.healthPoint + " HP");
        System.out.println(nama + " memiliki HP " + healthPoint + " HP");
    }

    @Override
    public void specialAttack(Monster target) {
        int actualDamage = 15; // Random damage between 50% to 100% of base

        boolean miss = Math.random() < 0.1; // 10% chance of missing

        if (miss) {
            System.out.println("Special attack missed!");
        } else {
            target.healthPoint -= actualDamage;
            int sacrificeHP = (int) (healthPoint * 0.1);
            healthPoint -= sacrificeHP;

            System.out.println("WUSSSSSHHH!!!!!!......");
            System.out.println(nama + " melakukan serangan spesial ke " + target.nama);
            System.out.println("Serangan mengurangi " + actualDamage + " HP pada " + target.nama);
            System.out.println(target.nama + " sekarang memiliki " + target.healthPoint + " HP");
            System.out.println(nama + " memiliki HP " + healthPoint + " HP");
        }
    }

    @Override
    public void elementalAttack(Monster target) {
        if (element != null && target.element != null) {
            int effectiveness = determineElementalEffectiveness(target.element);
            int baseDamage = 20; // Adjust this calculation as needed
            int actualDamage = baseDamage * effectiveness;

            target.healthPoint -= actualDamage;
            int sacrificeHP = (int) (healthPoint * 0.2); // 20% sacrifice
            healthPoint -= sacrificeHP;

            System.out.println("WINGGGGG!!!!!!.....");
            System.out.println(nama + " melakukan serangan elemen ke " + target.nama);
            System.out.println("Serangan mengurangi " + actualDamage + " HP pada " + target.nama);
            System.out.println(target.nama + " sekarang memiliki " + target.healthPoint + " HP");
            System.out.println(nama + " memiliki HP " + healthPoint + " HP");

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
                    return element.getNama().equalsIgnoreCase("api") ? 2 : 1;
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
                    target.setElement(elementList);

                    int baseDamage = level * 10; // Example base damage
                    int actualDamage = baseDamage;
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
        boolean success = Math.random() < 0.3; // 30% chance of success

        if (success) {
            System.out.println("Successfully fled from the battle.");
            System.out.println("Kamu kalah");
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
        if (!hasEvolved && element.get(0).getEvolutionOptions().contains(newElement)) {
            element.set(0, newElement);
            hasEvolved = true;
            System.out.println(getNama() + " has evolved. New element: " + newElement.getNama());
        } else if (hasEvolved) {
            System.out.println(getNama() + " has already evolved this level.");
        } else {
            System.out.println("Cannot evolve. Evolution to " + newElement.getNama() + " is not allowed.");
        }
    }

    @Override
    protected void performRandomAttack(Monster myMonster) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'performRandomAttack'");
    }
}
