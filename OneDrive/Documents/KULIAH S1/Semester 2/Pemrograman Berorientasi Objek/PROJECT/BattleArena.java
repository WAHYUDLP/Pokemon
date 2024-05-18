import java.util.List;
import java.util.Scanner;

public class BattleArena {
    private boolean healthItemUsed = false;

    public void startBattle(Monster myMonster, Monster wildMonster) {
        System.out.println("Welcome to the Battle Arena!");
        System.out.println("Battle started between " + myMonster.getNama() + " and " + wildMonster.getNama()+".");

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
                        if (myMonster instanceof PlayerMonster) {
                            useItem(scanner, (PlayerMonster) myMonster);
                        } else {
                            System.out.println("Only player-controlled monsters can use items.");
                        }
                        break;
                    case 5:
                        if (myMonster.flee()) {
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
                if (myMonster.isFainted()) {
                    System.out.println("Your monster has fainted!");
                } else {
                    System.out.println("You defeated the wild monster! Gaining experience...");
                    myMonster.gainExperiencePoints(35);
                    myMonster.incrementWins(); // Increment wins after winning a battle
                }
                battleEnded = true;
            }
        }

        healthItemUsed = false; // Reset the flag for the next battle
    }

    private void useItem(Scanner scanner, PlayerMonster playerMonster) {
        System.out.println("Available Items: 1. Health Potion (+20 HP), 2. Elemental Potion (Change Element)");
        System.out.println("Select an item to use:");
        int itemChoice = scanner.nextInt();
        scanner.nextLine();

        switch (itemChoice) {
            case 1: // Health Potion
                Item healthPotion = new Item("Health Potion", 20, 0);
                if (playerMonster.hasItem(healthPotion)) {
                    playerMonster.setHealthPoint(playerMonster.getHealthPoint() + 20); // Assuming +20 HP for health
                                                                                       // potion
                    playerMonster.useItem(healthPotion);
                    System.out.println(playerMonster.getNama() + " used Health Potion. Gained 20 HP.");
                } else {
                    System.out.println("Health Potion not purchased.");
                }
                break;
            case 2: // Elemental Potion
                Item elementalPotion = new Item("Elemental Potion", 0, 0);
                if (playerMonster.hasItem(elementalPotion)) {
                    System.out.println("Select an element: 1. Fire, 2. Ice, 3. Wind, 4. Earth, 5. Water");
                    int elementChoice = scanner.nextInt();
                    scanner.nextLine();
                    applyElementalPotion(playerMonster, elementChoice);
                    playerMonster.useItem(elementalPotion);
                } else {
                    System.out.println("Elemental Potion not purchased.");
                }
                break;
            default:
                System.out.println("Invalid item selection.");
                break;
        }
    }

    private void applyElementalPotion(PlayerMonster playerMonster, int elementChoice) {
        Element newElement = switch (elementChoice) {
            case 1 -> Element.FIRE;
            case 5 -> Element.WATER;
            case 3 -> Element.WIND;
            case 4 -> Element.EARTH;
            case 2 -> Element.ICE;
            default -> null;
        };
        if (newElement != null) {
            playerMonster.setElement(List.of(newElement));
            System.out.println(playerMonster.getNama() + " changed element to " + newElement.getNama() +".");
        } else {
            System.out.println("Invalid element choice. Potion has no effect.");
        }
    }

}
