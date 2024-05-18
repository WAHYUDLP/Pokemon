import java.util.List;
import java.util.Scanner;

public class HomeBase {

    public HomeBase() {
    }

    public void checkAndLevelUpMonster(PlayerMonster monster) {
        int wins = monster.getWins();
        int expPoints = monster.getExpPoint();

        if (wins >= 5 && expPoints >= 50) {
            int levelsGained = wins / 5;
            int remainingWins = wins % 5;
            try {
                monster.setLevel(monster.getLevel() + levelsGained);
                monster.setWins(remainingWins); // Sisakan kemenangan yang tidak terpakai
                monster.setExpPoint(expPoints - 50); // Kurangi 50 EP untuk naik level
                monster.setHasEvolved(false);
                System.out.println(monster.getNama() + " has leveled up to " + monster.getLevel() + ".");
            } catch (LevelOutOfBoundsException e) {
                System.out.println("Cannot level up: " + e.getMessage());
            }
        } else {
            System.out.println(monster.getNama() + " is currently at level " + monster.getLevel() + ".");
            System.out
                    .println(monster.getNama() + " needs to win every 5 battles and have at least 50 EP to level up.");
            System.out.println("Your wins: " + monster.getWins() + ", Your EP: " + monster.getExpPoint());
        }
    }
    // public void evolveMonster(PlayerMonster monster, Element newElement) {
    // if (!monster.getElement().isEmpty() && !monster.hasEvolved()) {
    // Element currentElement = monster.getElement().get(0);
    // List<Element> evolutionOptions = currentElement.getEvolutionOptions();
    // if (evolutionOptions != null && evolutionOptions.contains(newElement)) {
    // monster.setElement(List.of(newElement));
    // monster.setHasEvolved(true);
    // System.out.println(monster.getNama() + " has evolved into " +
    // newElement.getNama() + ".");
    // } else if (monster.hasEvolved()) {
    // System.out.println(monster.getNama() + " has already evolved this level.");
    // } else if (monster.getElement().isEmpty()) {
    // System.out.println("The monster has no initial element.");
    // } else {
    // System.out.println("Evolution to " + newElement.getNama() + " is not allowed
    // from " + currentElement.getNama() + ".");
    // }
    // }
    // }
    

    public void evolveMonster(PlayerMonster monster, Element newElement) {
        if (!monster.getElement().isEmpty() && !monster.hasEvolved()
                && monster.getElement().get(0).getEvolutionOptions().contains(newElement)) {
            monster.setElement(List.of(newElement));
            monster.setHasEvolved(true);
            System.out.println(monster.getNama() + " has evolved into " + newElement.getNama() + ".");
        } else if (monster.hasEvolved()) {
            System.out.println(monster.getNama() + " has already evolved this level.");
        } else if (monster.getElement().isEmpty()) {
            System.out.println("The monster has no initial element.");
        } else {
            System.out.println("Evolution to " + newElement.getNama() + " is not allowed from "
                    + monster.getElement().get(0).getNama() + ".");
        }
    }

    public void healMonster(Monster monster) {
        monster.setHealthPoint(100);
        System.out.println(monster.getNama() + " has been fully healed.");
    }

    public void buyItem(PlayerMonster monster, Item item) {
        if (monster.getExpPoint() >= 20) {
            monster.setExpPoint(monster.getExpPoint() - 20);
            monster.addItem(item);
            System.out.println("Bought " + item.getNama() + ".");
        } else {
            System.out.println("Not enough EP to buy " + item.getNama() + ", you must have minimum 20 EP.");
        }
    }

    public void checkEP(PlayerMonster monster) {
        System.out.println(monster.getNama() + " has " + monster.getExpPoint() + " EP.");
    }

    public void checkLevel(PlayerMonster monster) {
        System.out.println(monster.getNama() + " is at level " + monster.getLevel() + ".");
    }

    public void checkHP(PlayerMonster monster) {
        System.out.println(monster.getNama() + " has " + monster.getHealthPoint() + " HP.");
    }

    public void enterHomeBase(PlayerMonster playerMonster) {
        System.out.println("Welcome back to Home Base!");
        Scanner scanner = new Scanner(System.in);

        boolean done = false;

        while (!done) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Check HP and EP ");
            System.out.println("2. Check and Level Up Monster");
            System.out.println("3. Heal Monster");
            System.out.println("4. Evolve Monster");
            System.out.println("5. Buy Item");
            System.out.println("6. Exit and Save Home Base");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 2:
                    checkAndLevelUpMonster(playerMonster);
                    break;
                case 4:
                    System.out.println("Select an element to evolve to: 1. Fire, 2. Wind, 3. Water, 4. Ice, 5. Earth");
                    int elementChoice = scanner.nextInt();
                    scanner.nextLine();
                    Element newElement = switch (elementChoice) {
                        case 1 -> Element.FIRE;
                        case 3 -> Element.WATER;
                        case 2 -> Element.WIND;
                        case 5 -> Element.EARTH;
                        case 4 -> Element.ICE;
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
                    checkHP(playerMonster);
                    checkEP(playerMonster);
                    break;
                case 6:
                    GameProgress.saveProgress(playerMonster);
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
                    System.out.println("Choose an element to apply: Fire, Ice, Wind, Earth, Water");
                    String input = scanner.nextLine().toUpperCase();
                    Element newElement = switch (input) {
                        case "FIRE" -> Element.FIRE;
                        case "WATER" -> Element.WATER;
                        case "EARTH" -> Element.EARTH;
                        case "WIND" -> Element.WIND;
                        case "ICE" -> Element.ICE;
                        default -> null;
                    };
                    if (newElement != null) {
                        buyItem(playerMonster, new Item("Elemental Potion", 0, 0));

                    } else {
                        System.out.println("Invalid element choice. Potion has no effect.");
                    }
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

class LevelOutOfBoundsException extends Exception {
    public LevelOutOfBoundsException(String message) {
        super(message);
    }
}