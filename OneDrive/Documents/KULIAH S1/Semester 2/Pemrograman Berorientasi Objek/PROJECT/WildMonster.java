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
        int damage = isAggressive ? random.nextInt(10) : random.nextInt(5) + 1; // Generates damage 1-25 if aggressive, 1-10 if not
        target.healthPoint -= damage;
    
        System.out.println("SWINGG\n");
        System.out.println("Monstermu diserang sebanyak " + damage + " HP!");
        System.out.println(target.nama + " memiliki " + target.healthPoint + " HP");
        System.out.println(nama + " memiliki " + healthPoint + " HP");
    }
    

    @Override
    public void specialAttack(Monster target) {
        int baseDamage = isAggressive ? 5 : 1;  // Base damage is lower if not aggressive
        int actualDamage = random.nextInt(isAggressive ? 13 : 8) + baseDamage;  // Random damage 5-25 if aggressive, 1-10 if not
    
        boolean miss = Math.random() < 0.1;  // 10% chance of missing
    
        if (miss) {
            System.out.println("Special attack missed!");
        } else {
            target.healthPoint -= actualDamage;
    
            System.out.println("BOM....\n");
            System.out.println("Monstermu diserang sebanyak " + actualDamage + " HP!!");
            System.out.println(target.nama + " memiliki " + target.healthPoint + " HP");
            System.out.println(nama + " memiliki " + healthPoint + " HP");
        }
    }
    

    @Override
    public void elementalAttack(Monster target) {
        if (element != null && target.element != null) {
            int baseDamage = isAggressive ? 7 : 1;  // Base damage is lower if not aggressive
            int actualDamage = random.nextInt(isAggressive ? 14 : 10) + baseDamage;  // Random damage 7-25 if aggressive, 1-10 if not
    
            target.healthPoint -= actualDamage;
    
            System.out.println("DUAR...\n");
            System.out.println("Monstermu diserang sebanyak " + actualDamage + " HP!!");
            System.out.println(target.nama + " memiliki " + target.healthPoint + " HP");
            System.out.println(nama + " memiliki HP " + healthPoint + " HP");
        } else {
            System.out.println("Tidak dapat menyerang, elemen tidak ada");
        }
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

}
