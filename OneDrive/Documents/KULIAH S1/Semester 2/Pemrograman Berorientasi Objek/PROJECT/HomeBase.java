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
        System.out.println(monster.getNama() + " is currently at level " + monster.getLevel() + ".");

        // Periksa apakah monster memenuhi syarat untuk naik level
        if (monster.getWins() >= 5) {
            // Proses naik level
            monster.setLevel(monster.getLevel() + 1);
            monster.setWins(monster.getWins() - 5); // Reset jumlah kemenangan setelah naik level
            monster.setHasEvolved(false); // Reset status evolusi
            System.out.println(monster.getNama() + " has leveled up to " + monster.getLevel());
        } else {
            // Tampilkan pesan jika belum memenuhi syarat
            System.out.println(monster.getNama() + " needs to win at least 5 battles to level up.");
        }
    }
    public void evolveMonster(PlayerMonster monster, Element newElement) {
        if (!monster.getElement().isEmpty() && !monster.hasEvolved() && monster.getElement().get(0).getEvolutionOptions().contains(newElement)) {
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
        System.out.println(monster.getNama() + " is currently at level " + monster.getLevel() + ".");
    }

    public void checkHP(PlayerMonster monster) {
        System.out.println(monster.getNama() + " currently has " + monster.getHealthPoint() + " HP.");
    }

    public void enterHomeBase(PlayerMonster playerMonster) {
        System.out.println("Welcome back to Home Base!");
        Scanner scanner = new Scanner(System.in);

        boolean done = false;

        while (!done) {

            System.out.println("\nWhat would you like to do?");

            System.out.println("1. Check HP"); // Option to check HP
            System.out.println("2. Check EP");
            System.out.println("3. Check and Level Up Monster");
            System.out.println("4. Heal Monster");
            System.out.println("5. Evolve Monster");
            System.out.println("6. Buy Item");
            System.out.println("7. Exit Home Base and Save Proggress");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {

                case 3:
                    // levelUpMonster(playerMonster);
                    checkAndLevelUpMonster(playerMonster);
                    break;
                case 5:
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
                case 4:
                    healMonster(playerMonster);
                    break;
                case 6:
                    buyItemOption(scanner, playerMonster);
                    break;
                case 2:
                    checkEP(playerMonster);
                    break;

                case 1:
                    checkHP(playerMonster); // Call the method to check HP
                    break;
                case 7:
                    GameProgress.saveProgress(playerMonster); // Save game progress
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
            System.out.println("Membeli " + item.getNama());
        } else {
            System.out.println("EP tidak cukup untuk membeli " + item.getNama() + ", Anda harus memiliki minimal 20 EP");
        }
    }

    private void buyItemOption(Scanner scanner, PlayerMonster playerMonster) {
        System.out.println("Item Tersedia: ");
        System.out.println("1. Health Potion (+20 HP)");
        System.out.println("2. Elemental Potion (Ganti Elemen)");

        System.out.print("Pilih item untuk dibeli: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Konsumsi newline

        switch (choice) {
            case 1:
                buyItem(playerMonster, new Item("Health Potion", 20, 0));
                break;
            case 2:
                System.out.println("Pilih elemen untuk diterapkan: FIRE, WATER, EARTH, AIR, ICE");
                String input = scanner.nextLine().toUpperCase();
                Element newElement = switch (input) {
                    case "FIRE" -> Element.FIRE;
                    case "WATER" -> Element.WATER;
                    case "EARTH" -> Element.EARTH;
                    case "AIR" -> Element.AIR;
                    case "ICE" -> Element.ICE;
                    default -> null;
                };

                if (newElement != null) {
                    Item elementalPotion = new Item("Elemental Potion", 0, 0);
                    buyItem(playerMonster, elementalPotion);
                    System.out.println("Item dibeli: " + elementalPotion.getNama());
                } else {
                    System.out.println("Pilihan elemen tidak valid. Potion tidak memiliki efek.");
                }
                break;
            default:
                System.out.println("Pilihan item tidak valid. Tidak ada item yang dibeli.");
                break;
        }
    }
}