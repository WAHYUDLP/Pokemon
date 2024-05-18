import java.util.List;
import java.util.Random;

public class WildMonster extends Monster {
    private boolean isAggressive;
    private Random random = new Random();

    public WildMonster(String nama, int level, List<Element> elements, boolean isAggressive) {
        super(nama, level, elements);
        this.isAggressive = isAggressive;
    }

    // Randomly perform an attack
    public void performRandomAttack(Monster target) {
        int attackType = random.nextInt(3) + 1; // Generates 1, 2, or 3

        switch (attackType) {
            case 1:
                this.basicAttack(target);
                break;
            case 2:
                this.specialAttack(target);
                break;
            case 3:
                if (this.getElement() != null && target.getElement() != null) {
                    this.elementalAttack(target);
                } else {
                    System.out.println(this.getNama() + " tried to perform an elemental attack but failed.");
                    this.basicAttack(target); // Fallback to basic attack if elemental conditions aren't met
                }
                break;
            default:
                this.basicAttack(target); // Fallback
                break;
        }
    }

    // Getter for name
    public String getNama() {
        return super.getNama();
    }

    public void setIsAggressive(boolean isAggressive) {
        this.isAggressive = isAggressive;
    }

    public boolean getIsAggressive() {
        return isAggressive;
    }
    @Override
    public void basicAttack(Monster target) {
        int damage = isAggressive ? random.nextInt(25) + 1 : random.nextInt(10) + 1; // Generates damage 1-25 if aggressive, 1-10 if not
        target.healthPoint -= damage;
    
        System.out.println("SWINGG\n");
        System.out.println("Your monster attacked and dealt " + damage + " HP damage!");
        System.out.println(target.nama + " now has " + target.healthPoint + " HP");
        System.out.println(nama + " has " + healthPoint + " HP remaining");
    }
    
    @Override
    public void specialAttack(Monster target) {
        int baseDamage = isAggressive ? 5 : 1;  // Base damage is lower if not aggressive
        int actualDamage = random.nextInt(isAggressive ? 21 : 10) + baseDamage;  // Random damage 5-25 if aggressive, 1-10 if not
    
        boolean miss = Math.random() < 0.1;  // 10% chance of missing
    
        if (miss) {
            System.out.println("Special attack missed!");
        } else {
            target.healthPoint -= actualDamage;
    
            System.out.println("BOM....\n");
            System.out.println("Your monster attacked and dealt " + actualDamage + " HP damage!!");
            System.out.println(target.nama + " now has " + target.healthPoint + " HP");
            System.out.println(nama + " has " + healthPoint + " HP remaining");
        }
    }
    
    @Override
    public void elementalAttack(Monster target) {
        if (element != null && target.element != null) {
            Element playerElement = element.get(0);  // Pastikan ini diinisialisasi dengan benar

            int baseDamage = isAggressive ? 7 : 1;  // Base damage is lower if not aggressive
            int actualDamage = random.nextInt(isAggressive ? 19 : 10) + baseDamage;  // Random damage 7-25 if aggressive, 1-10 if not
    
            target.healthPoint -= actualDamage;
    
            String attackMessage = getElementalAttackMessage(playerElement);
            System.out.println(attackMessage);

            System.out.println("Your monster attacked and dealt " + actualDamage + " HP damage!!");
            System.out.println(target.nama + " now has " + target.healthPoint + " HP");
            System.out.println(nama + " has " + healthPoint + " HP remaining");
        } else {
            System.out.println("Cannot perform elemental attack. Invalid elements.");
        }
    }

    private String getElementalAttackMessage(Element element) {
        switch (element.getNama().toUpperCase()) {
            case "FIRE":
                return "Blazing flames!";
            case "WIND":
                return "The wind roars!";
            case "WATER":
                return "Aqua splash!";
            case "ICE":
                return "Freezing cold!";
            case "EARTH":
                return "Earthquake shakes!";
            default:
                return "Elemental attack!";
        }
    }
    
    @Override
    public boolean flee() {
        boolean success = Math.random() < 0.3; // 30% chance of success

        if (success) {
            System.out.println("Successfully fled from the battle.");
   
        } else {
            System.out.println("Failed to flee. The battle continues.");
        }

        return success;
    }

    @Override
    public boolean isFainted() {
        if (healthPoint <= 0) {
            return "Menang wild" != null;
        }
        return healthPoint <= 0;
    }

    @Override
    public void gainExperiencePoints(int points) {
        // Wild monsters don't gain experience in this scenario.
        System.out.println("Wild monsters don't gain experience.");
    }

    @Override
    public void evolve(Element newElement) {
        // Wild monsters evolving can be left unimplemented.
        System.out.println("Wild monsters don't evolve.");
    }

    String getElements() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void useItem(Item item, Monster target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'useItem'");
    }

    @Override
    protected void incrementWins() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'incrementWins'");
    }

}
