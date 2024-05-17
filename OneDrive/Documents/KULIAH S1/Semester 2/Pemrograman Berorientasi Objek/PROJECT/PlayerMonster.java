import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayerMonster extends Monster {
    private static final long serialVersionUID = 1L;

    private Player owner;
    private int wins;
    private boolean hasEvolved;
    private List<Item> boughtItems; // Daftar item yang dibeli
    private List<Element> elements;

    public PlayerMonster() {
        super("", 1, new ArrayList<>()); // Call the superclass constructor with default values
        this.elements = new ArrayList<>();
        this.boughtItems = new ArrayList<>(); // Inisialisasi daftar boughtItems
    }

    public PlayerMonster(String nama, int level, List<Element> elements, Player owner) {
        super(nama, level, elements);
        this.owner = owner;
        this.wins = 0;
        this.hasEvolved = false;
        this.elements = elements != null ? elements : new ArrayList<>();
        this.boughtItems = new ArrayList<>(); // Inisialisasi daftar boughtItems
    }

    public void addItem(Item item) {
        this.boughtItems.add(item);
    }

    public boolean hasItem(Item item) {
        for (Item invItem : boughtItems) {
            if (invItem.getNama().equals(item.getNama())) {
                return true;
            }
        }
        return false;
    }

    public void useItem(Item item) {
        for (int i = 0; i < boughtItems.size(); i++) {
            if (boughtItems.get(i).getNama().equals(item.getNama())) {
                boughtItems.remove(i);
                break;
            }
        }
    }

    public List<Item> getBoughtItems() {
        return boughtItems;
    }

    public String getNama() {
        return super.getNama();
    }

    public void setNama(String nama) {
        super.setNama(nama);
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

    public void setElement(List<Element> elements) {
        this.elements = elements != null ? elements : new ArrayList<>();
    }

    public List<Element> getElement() {
        return elements != null ? elements : new ArrayList<>();
    }

    public void setHasEvolved(boolean hasEvolved) {
        this.hasEvolved = hasEvolved;
    }

    @Override
    public void basicAttack(Monster target) {
        int damage = level * 12; // Contoh: serangan dasar mengurangi 10 HP per level

        target.healthPoint -= damage;

        System.out.println("SIAAAAAA!!!!!!......");
        System.out.println(nama + " melakukan serangan dasar ke " + target.nama);
        System.out.println("Serangan mengurangi " + damage + " HP pada " + target.nama);
        System.out.println(target.nama + " sekarang memiliki " + target.healthPoint + " HP");
        System.out.println(nama + " memiliki HP " + healthPoint + " HP");
    }

    @Override
    public void specialAttack(Monster target) {
        int actualDamage = level * 15; // Random damage between 50% to 100% of base

        boolean miss = Math.random() < 0.1; // 10% chance of missing

        if (miss) {
            System.out.println("Special attack missed!");
        } else {
            target.healthPoint -= actualDamage;
            int sacrificeHP = (int) (healthPoint * 0.2);
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
            int baseDamage = level * 20; // Adjust this calculation as needed
            int actualDamage = baseDamage * effectiveness;

            target.healthPoint -= actualDamage;
            // int sacrificeHP = (int) (healthPoint * 0.2); // 20% sacrifice
            // healthPoint -= sacrificeHP;

            System.out.println("WINGGGGG!!!!!!.....");
            System.out.println(nama + " melakukan serangan elemen ke " + target.nama);
            System.out.println("Serangan mengurangi " + actualDamage + " HP pada " + target.nama);
            System.out.println(target.nama + " sekarang memiliki " + target.healthPoint + " HP");
            System.out.println(nama + " memiliki HP " + healthPoint + " HP");

        } else {
            System.out.println("Cannot perform elemental attack. Invalid elements.");
        }
    }

    @Override
    public void setLevel(int level) throws LevelOutOfBoundsException {
        super.setLevel(level);
        if (level < 1 || level > 99) {
            throw new LevelOutOfBoundsException("Level must be between 1 and 99.");
        }

    }

    private int determineElementalEffectiveness(List<Element> elements) {
        for (Element element : elements) {
            switch (element.getNama().toLowerCase()) {
                case "water":
                    return element.getNama().equalsIgnoreCase("fire") ? 2 : 1;
                case "earth":
                    return element.getNama().equalsIgnoreCase("water") ? 2 : 1;
                case "fire":
                    return element.getNama().equalsIgnoreCase("ice") ? 2 : 1;
                case "ice":
                    return element.getNama().equalsIgnoreCase("air") ? 2 : 1;
                case "air":
                    return element.getNama().equalsIgnoreCase("earth") ? 2 : 1;
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
            System.out.println("You Lose!");
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

    public void setWins(int wins) {
        this.wins = wins;
    }
}
