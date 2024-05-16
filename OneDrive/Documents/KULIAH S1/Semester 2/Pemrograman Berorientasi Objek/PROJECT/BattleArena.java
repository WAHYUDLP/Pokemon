
import java.util.Arrays;
import java.util.Scanner;

public class BattleArena {
    public void startBattle(Monster myMonster, Monster wildMonster) {
        System.out.println("Welcome to the Battle Arena!");
        System.out.println("Battle started between " + myMonster.getNama() + " and " + wildMonster.getNama());

        Scanner scanner = new Scanner(System.in);
        boolean battleEnded = false;
        boolean playerTurn = true; // Toggle to determine whose turn it is

        while (!battleEnded) {
            if (playerTurn) {
                System.out.println("\n" + myMonster.getNama() + "'s turn! Choose your action:");
                System.out.println("1. Basic Attack");
                System.out.println("2. Special Attack");
                System.out.println("3. Elemental Attack");
                System.out.println("4. Use Item");
                System.out.println("5. Flee");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        myMonster.basicAttack(wildMonster);
                        break;
                    case 2:
                        myMonster.specialAttack(wildMonster);
                        break;
                    case 3:
                        myMonster.elementalAttack(wildMonster);
                        break;
                    case 4:
                    useItem(scanner, myMonster);
                        break;
                    case 5:
                        if (myMonster.flee()) {
                            System.out.println("Successfully fled from the battle!");
                            battleEnded = true;
                        } else {
                            System.out.println("Failed to flee!");
                        }
                        continue;
                    default:
                        System.out.println("Invalid choice! Please choose again.");
                        continue;
                }
            } else {
                System.out.println("\n" + wildMonster.getNama() + "'s turn!");
                wildMonster.performRandomAttack(myMonster);
            }

            playerTurn = !playerTurn; // Switch turns

            // Check if battle ended
            if (myMonster.isFainted() || wildMonster.isFainted()) {
                System.out.println(myMonster.isFainted() ? "Your monster has fainted!"
                        : "You defeated the wild monster! Gaining experience...");
                if (!myMonster.isFainted()) {
                    myMonster.gainExperiencePoints(50);
                }
                battleEnded = true;
            }
        }

        scanner.close(); // Close scanner to prevent resource leak
    }
     private void useItem(Scanner scanner, Monster myMonster) {
        System.out.println("Available Items: 1. Health Potion, 2. Elemental Potion");
        System.out.println("Select an item to use:");
        int itemChoice = scanner.nextInt();
        scanner.nextLine();

        switch (itemChoice) {
            case 1: // Health Potion
                myMonster.setHealthPoint(myMonster.getHealthPoint() + 20); // Assuming +20 HP for health potion
                System.out.println(myMonster.getNama() + " used Health Potion. Gained 20 HP.");
                break;
            case 2: // Elemental Potion
                System.out.println("Select an element: 1. Fire, 2. Water, 3. Air, 4. Earth, 5. Ice");
                int elementChoice = scanner.nextInt();
                scanner.nextLine();
                applyElementalPotion(myMonster, elementChoice);
                break;
            default:
                System.out.println("Invalid item selection.");
                break;
        }
    }

    private void applyElementalPotion(Monster myMonster, int elementChoice) {
        Element newElement = null;
        switch (elementChoice) {
            case 1:
                newElement = Element.API;
                break;
            case 2:
                newElement = Element.AIR;
                break;
            case 3:
                newElement = Element.ES;
                break;
            case 4:
                newElement = Element.TANAH;
                break;
            case 5:
                newElement = Element.ANGIN;
                break;
            default:
                System.out.println("Invalid element selection.");
                return;
        }
        myMonster.setElement(Arrays.asList(newElement));
        System.out.println(myMonster.getNama() + " has changed to " + newElement.getNama() + " element.");
    }

}
