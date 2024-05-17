import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HomeBase {
    private List<Monster> storedMonsters;

    public HomeBase() {
        storedMonsters = new ArrayList<>();
    }

    public void checkAndLevelUpMonster(PlayerMonster monster) {
        // Tampilkan level saat ini

        int wins = monster.getWins();

        // Periksa apakah monster memenuhi syarat untuk naik level
        if (wins >= 5) {
            // Proses naik level
            int levelsGained = wins / 5;
            int remainingWins = wins % 5;

            monster.setLevel(monster.getLevel() + levelsGained);
            // monster.setWins(remainingWins); // Sisakan kemenangan yang tidak terpakai
            monster.setHasEvolved(false); // Reset status evolusi

            System.out.println(monster.getNama() + " has leveled up to " + monster.getLevel() + ".");
        } else {
            // Tampilkan pesan jika belum memenuhi syarat
            System.out.println(monster.getNama() + " is currently at level " + monster.getLevel() + ".");
            System.out.println(
                    monster.getNama() + " needs to win every 5 battles to level up. Your wins: " + monster.getWins());
        }
    }

    public void evolveMonster(PlayerMonster monster, Element newElement) {
        if (!monster.getElement().isEmpty() && !monster.hasEvolved()
                && monster.getElement().get(0).getEvolutionOptions().contains(newElement)) {
            monster.setElement(List.of(newElement));
            monster.setHasEvolved(true);
            System.out.println(monster.getNama() + " has evolved into " + newElement.getNama());

        } else if (monster.hasEvolved()) {
            System.out.println(monster.getNama() + " has already evolved this level.");
        } else if (monster.getElement().isEmpty()) {
            System.out.println("The monster has no initial element.");
        } else {
            System.out.println("Evolution to " + newElement.getNama() + " is not allowed from "
                    + monster.getElement().get(0).getNama());
        }
    }

    public void healMonster(Monster monster) {
        monster.setHealthPoint(100); // Misalnya sembuh ke full HP
        System.out.println(monster.getNama() + " has been fully healed.");
    }

    public void buyItem(Monster monster, Item item) {
        if (monster.getExpPoint() >= 20) {
            monster.setExpPoint(monster.getExpPoint() - 20);
            System.out.println("Bought " + item.getNama());
        } else {
            System.out.println("Not enough EP to buy " + item.getNama() + ", you must have minimum 20 EP");
        }
    }

    public void checkEP(PlayerMonster monster) {
        System.out.println(monster.getNama() + " has " + monster.getExpPoint() + " EP.");
    }

    public void checkLevel(PlayerMonster monster) {
        System.out.println(monster.getNama() + " is at level " + monster.getLevel() + ".");
    }

    public void checkHP(PlayerMonster monster) {
        System.out.println(monster.getNama() + "  has " + monster.getHealthPoint() + " HP.");
    }

    public void enterHomeBase(PlayerMonster playerMonster) {
        System.out.println("Welcome back to Home Base!");
        Scanner scanner = new Scanner(System.in);

        boolean done = false;

        while (!done) {

            System.out.println("\nWhat would you like to do?");

            System.out.println("1. Check HP and EP "); // Option to check HP
            System.out.println("2. Check and Level Up Monster");
            System.out.println("3. Heal Monster");
            System.out.println("4. Evolve Monster");
            System.out.println("5. Buy Item");
            System.out.println("6. Exit Home Base");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {

                case 2:
                    // levelUpMonster(playerMonster);
                    checkAndLevelUpMonster(playerMonster);
                    break;
                case 4:
                    System.out.println("Select an element to evolve to: 1. Fire, 2. Water, 3. Air, 4. Earth, 5. Ice");
                    int elementChoice = scanner.nextInt();
                    scanner.nextLine();
                    Element newElement = switch (elementChoice) {
                        case 1 -> Element.FIRE;
                        case 2 -> Element.WATER;
                        case 3 -> Element.AIR;
                        case 4 -> Element.EARTH;
                        case 5 -> Element.ICE;
                        default -> null;
                    };
                    if (newElement != null) {
                        evolveMonster(playerMonster, newElement);
                    } else {
                        System.out.println("Invalid element choice.");
                    }
                    break;
                case 3:
                    healMonster(playerMonster);
                    break;
                case 5:
                    buyItemOption(scanner, playerMonster);
                    break;
                case 1:
                    checkHP(playerMonster); // Call the method to check HP
                    checkEP(playerMonster);
                    break;

                case 6:
                    // GameProgress.saveProgress(playerMonster); // Save game progress
                    done = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
                    break;
            }
        }
    }

    public void buyItem(PlayerMonster playerMonster, Item item) {
        if (playerMonster.getExpPoint() >= 20) {
            playerMonster.setExpPoint(playerMonster.getExpPoint() - 20);
            playerMonster.addItem(item);
            System.out.println("Buy item: " + item.getNama());
        } else {
            System.out.println("Your EP not enough " + item.getNama() + ", you must have minimum 20 EP");
        }
    }

    private void buyItemOption(Scanner scanner, PlayerMonster playerMonster) {

        System.out.println("Available Items: ");
        System.out.println("1. Health Potion (+20 HP)");
        System.out.println("2. Elemental Potion (Change Element)");
        System.out.println("3. Exit");

        boolean buyRunning = true;
        while (buyRunning) {
            System.out.print("Choose an item to buy: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    buyItem(playerMonster, new Item("Health Potion", 20, 0));
                    break;
                case 2:
                    System.out.println("Choose an element to apply: Fire, Water, Earth, Water, Ice");
                    String input = scanner.nextLine().toUpperCase(); // Ambil input dan ubah ke huruf besar
                    Element newElement = null;

                    switch (input) {
                        case "FIRE":
                            newElement = Element.FIRE;
                            break;
                        case "WATER":
                            newElement = Element.WATER;
                            break;
                        case "EARTH":
                            newElement = Element.EARTH;
                            break;
                        case "AIR":
                            newElement = Element.AIR;
                            break;
                        case "ICE":
                            newElement = Element.ICE;
                            break;

                        default:
                            System.out.println("Invalid element choice. Potion has no effect.");
                            return;
                    }

                    Item elementalPotion = new Item("Elemental Potion", 0, 0);
                    buyItem(playerMonster, elementalPotion);
                    break;

                case 3:
                    buyRunning = false;
                    break;
                default:
                    System.out.println("Invalid item choice. No item bought.");
                    break;
            }
        }

    }
}