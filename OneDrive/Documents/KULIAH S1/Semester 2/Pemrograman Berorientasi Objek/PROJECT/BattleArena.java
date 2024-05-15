import java.util.Scanner;

public class BattleArena {
    PlayerMonster monsterPlayer;
    WildMonster wildMonster;
    HomeBase homeBase;

    public BattleArena(PlayerMonster monsterPlayer, WildMonster wildMonster) {
        this.monsterPlayer = monsterPlayer;
        this.wildMonster = wildMonster;
    }

    public void startBattle(Monster myMonster, Monster monster) {
        System.out.println("Welcome to the Battle Arena!");
        System.out.println("Your opponent is a wild monster!");
        System.out.println("Let the battle begin!");

        Scanner scanner = new Scanner(System.in);
        boolean battleEnded = false;

        while (!battleEnded) {
            System.out.println("\nChoose your action:");
            System.out.println("1. Basic Attack");
            System.out.println("2. Special Attack");
            System.out.println("3. Elemental Attack");
            System.out.println("4. Use Item");
            System.out.println("5. Flee");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    monsterPlayer.basicAttack(monster);
                    break;
                case 2:
                    monsterPlayer.specialAttack(monster);
                    break;
                case 3:
                    monsterPlayer.elementalAttack(monster);
                    break;
                case 4:
                    // Implement item usage logic
                    break;
                case 5:
                    if (monsterPlayer.isFainted()) {
                        System.out.println("You are too weak to flee!");
                    } else {
                        boolean escaped = monsterPlayer.flee();
                        if (escaped) {
                            System.out.println("You successfully fled the battle!");
                            battleEnded = true;
                        } else {
                            System.out.println("You failed to flee!");
                        }
                    }
                    break;
                default:
                    System.out.println("Invalid choice! Please choose again.");
                    break;
            }

            // Check if battle ended
            if (monsterPlayer.isFainted() || monster.isFainted()) {
                battleEnded = true;
            }
        }

        exitBattle();
    }

    // public void performAction(Action action) {
    // // Implement action logic
    // }

    public void exitBattle() {
        System.out.println("The battle has ended.");
        if (monsterPlayer.isFainted()) {

            System.out.println("Your monster has fainted!");
        } else if (wildMonster.isFainted()) {
            System.out.println("You defeated the wild monster!");
        } else {
            System.out.println("Exiting battle...");
        }
        System.out.println("Returning to home base...");

        returnToHomeBase();

    }

    private void returnToHomeBase() {
        System.out.println("Returning to home base...");
        homeBase.enterHomeBase(monsterPlayer);
    }

  
}
