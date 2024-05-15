import java.util.List;

public class WildMonster extends Monster {
    private boolean isAggressive;

    public WildMonster(String nama, int level, List<Element> elements, boolean isAggressive) {
        super(nama, level, elements);
        this.isAggressive = isAggressive;
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
        int damage = level * 10; // Example: basic attack reduces 5 HP per level
        target.healthPoint -= damage;

        System.out.println(nama + " melakukan serangan dasar ke " + target.nama);
        System.out.println("Serangan mengurangi " + damage + " HP pada " + target.nama);
        System.out.println(target.nama + " sekarang memiliki " + target.healthPoint + " HP");
    }

    @Override
    public void specialAttack(Monster target) {
        int baseDamage = level * 10; // Example base damage
        int actualDamage = (int) (baseDamage * (Math.random() + 0.5)); // Random damage between 50% to 100% of base

        boolean miss = Math.random() < 0.1; // 10% chance of missing

        if (miss) {
            System.out.println("Special attack missed!");
        } else {
            target.healthPoint -= actualDamage;
            System.out.println("Special attack hits! Target loses " + actualDamage + " HP.");
            int sacrificeHP = (int) (healthPoint * 0.2); // 20% sacrifice
            healthPoint -= sacrificeHP;
            System.out.println("You sacrificed " + sacrificeHP + " HP.");
        }
    }

    @Override
    public void elementalAttack(Monster target) {
        if (element != null && target.element != null) {
            int baseDamage = level * 10; // Example base damage
            int actualDamage = baseDamage; // Example: always full damage for elemental attack

            target.healthPoint -= actualDamage;

            System.out.println("Elemental attack hits! Target loses " + actualDamage + " HP.");
        } else {
            System.out.println("Cannot perform elemental attack. Invalid elements.");
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void useItem(Item item, Monster target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'useItem'");
    }
}
