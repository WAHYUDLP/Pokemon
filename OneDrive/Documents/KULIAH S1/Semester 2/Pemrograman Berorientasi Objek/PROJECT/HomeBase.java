import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HomeBase {
    private List<Monster> storedMonsters;

    public HomeBase() {
        storedMonsters = new ArrayList<>();
    }

    public void storeMonster(Monster monster) {
        storedMonsters.add(monster);
    }

    public void retrieveMonster(Monster monster) {
        storedMonsters.remove(monster);
    }

    public void levelUpMonster(PlayerMonster monster) {
        if (monster.getWins() > 0) {
            monster.setLevel(monster.getLevel() + 1);
           // monster.decrementWins(); // Decrease win count by 1
            monster.setHasEvolved(false); // Reset evolve status
            System.out.println(monster.getNama() + " has leveled up to " + monster.getLevel());
        } else {
            System.out.println("Your level now " + monster.getLevel());
            System.out.println(monster.getNama() + " Kamu harus memenangkan game untuk dapat naik 1 level");
        }
    }

    public void evolveMonster(PlayerMonster monster, Element newElement) {
        if (!monster.hasEvolved() && monster.getElement().get(0).getEvolutionOptions().contains(newElement)) {
            monster.setElement(List.of(newElement));
            monster.setHasEvolved(true);
            System.out.println(monster.getNama() + " has evolved into " + newElement.getNama());
        } else if (monster.hasEvolved()) {
            System.out.println(monster.getNama() + " has already evolved this level.");
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
            System.out.println("Not enough EP to buy " + item.getNama());
        }
    }

    public void checkEP(PlayerMonster monster) {
        System.out.println(monster.getNama() + " has " + monster.getExpPoint() + " EP.");
    }

    public void enterHomeBase(PlayerMonster playerMonster) {
        System.out.println("Welcome back to Home Base!");
        Scanner scanner = new Scanner(System.in);

        boolean done = false;
        while (!done) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Store Monster");
            System.out.println("2. Retrieve Monster");
            System.out.println("3. Level Up Monster");
            System.out.println("4. Evolve Monster");
            System.out.println("5. Heal Monster");
            System.out.println("6. Buy Item");
            System.out.println("7. Check EP");
            System.out.println("8. Exit Home Base");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    storeMonster(playerMonster);
                    break;
                case 2:
                    retrieveMonster(playerMonster);
                    break;
                case 3:
                    levelUpMonster(playerMonster);
                    break;
                case 4:
                    System.out.println("Select an element to evolve to: 1. Fire, 2. Water, 3. Air, 4. Earth, 5. Ice");
                    int elementChoice = scanner.nextInt();
                    scanner.nextLine();
                    Element newElement = switch (elementChoice) {
                        case 1 -> Element.API;
                        case 2 -> Element.AIR;
                        case 3 -> Element.ES;
                        case 4 -> Element.TANAH;
                        case 5 -> Element.ANGIN;
                        default -> null;
                    };
                    if (newElement != null) {
                        evolveMonster(playerMonster, newElement);
                    } else {
                        System.out.println("Invalid element choice.");
                    }
                    break;
                case 5:
                    healMonster(playerMonster);
                    break;
                case 6:
                    buyItemOption(scanner, playerMonster);
                    break;
                case 7:
                    checkEP(playerMonster);
                    break;
                case 8:
                    done = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
                    break;
            }
        }
    }

    private void buyItemOption(Scanner scanner, PlayerMonster playerMonster) {
        System.out.println("Available Items: ");
        System.out.println("1. Health Potion (+20 HP)");
        System.out.println("2. Elemental Potion (Change Element)");

        System.out.print("Choose an item to buy: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                buyItem(playerMonster, new Item("Health Potion", 20, 0));
                break;
            case 2:
                System.out.println("Choose an element to apply: FIRE, WATER, EARTH, AIR, ICE");
                String input = scanner.nextLine().toUpperCase(); // Ambil input dan ubah ke huruf besar
                Element newElement = null;

                switch (input) {
                    case "FIRE":
                        newElement = Element.API;
                        break;
                    case "WATER":
                        newElement = Element.AIR;
                        break;
                    case "EARTH":
                        newElement = Element.TANAH;
                        break;
                    case "AIR":
                        newElement = Element.ANGIN;
                        break;
                    case "ICE":
                        newElement = Element.ES;
                        break;
                    default:
                        System.out.println("Invalid element choice. Potion has no effect.");
                        return;
                }

                Item elementalPotion = new Item("Elemental Potion", 0, 0);
                buyItem(playerMonster, elementalPotion);
                break;
            default:
                System.out.println("Invalid item choice. No item bought.");
                break;
        }
    }
}
