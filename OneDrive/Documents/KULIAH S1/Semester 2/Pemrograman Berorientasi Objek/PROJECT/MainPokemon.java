import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MainPokemon {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws LevelOutOfBoundsException {
        try {
            System.out.println("Welcome to Pokemon World!");

            // Load game progress at the start 
            PlayerMonster playerMonster = GameProgress.loadProgress();
            if (playerMonster == null) {
                // If no previous game progress is found, create a new player and player monster
                System.out.print("Enter your player name: ");
                String playerName = scanner.nextLine();

                Player player = new Player(playerName);
                System.out.println("Player " + playerName + " starts the adventure!");

                playerMonster = new PlayerMonster("Pokemon", 1, List.of(Element.WATER), player);
            }

            // Tampilkan informasi tentang monster pemain
            displayMonsterInfo(playerMonster);

            HomeBase homeBase = new HomeBase();
            BattleArena battleArena = new BattleArena(); // Create BattleArena object
            Dungeon dungeon = new Dungeon(battleArena); // Pass BattleArena object to Dungeon

            boolean gameRunning = true;
            while (gameRunning) {
                System.out.println("\nWhat do you want to do?");
                System.out.println("1. Enter Home Base");
                System.out.println("2. Enter Dungeon");
                System.out.println("3. Exit Game");

                try {
                    int choice = getNextInt();
                    scanner.nextLine(); // Consume the newline character after nextInt
                    switch (choice) {
                        case 1:
                            homeBase.enterHomeBase(playerMonster);
                            break;
                        case 2:
                            System.out.println("Welcome to Dungeon");
                            dungeon.explore(playerMonster);
                            break;
                        case 3:
                            System.out.println("Do you want to save or delete progress?");
                            System.out.println("1. Save Progress");
                            System.out.println("2. Delete Progress");
                            System.out.println("3. Cancel");

                            int exitChoice = getNextInt();
                            scanner.nextLine(); // Consume the newline character after nextInt

                            if (exitChoice == 1) {
                                GameProgress.saveProgress(playerMonster);
                                System.out.println("Exiting game...");
                                gameRunning = false;
                            } else if (exitChoice == 2) {
                                GameProgress.deleteProgress();
                                System.out.println("Exiting game...");
                                gameRunning = false;
                            } else if (exitChoice == 3) {
                                System.out.println("Canceling exit...");
                            } else {
                                System.out.println("Invalid choice. Please choose again.");
                            }
                            break;
                        default:
                            System.out.println("Invalid choice. Please choose again.");
                            break;
                    }
                } catch (GameActionException e) {
                    System.err.println(e.getMessage());
                }
            }
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    private static int getNextInt() throws GameActionException {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine(); // consume the incorrect input
            throw new GameActionException("Invalid input. Please enter a valid number.");
        } catch (NoSuchElementException e) {
            throw new GameActionException("Input stream closed unexpectedly.");
        }
    }

    // Metode untuk menampilkan informasi tentang monster pemain
    public static void displayMonsterInfo(PlayerMonster monster) {
        System.out.println("\nMonster Information:");
        System.out.println("Name                \t: " + monster.getNama());
        System.out.println("Level               \t: " + monster.getLevel());
        System.out.println("Experience Points   \t: " + monster.getExpPoint());
        System.out.println("Health Points       \t: " + monster.getHealthPoint());
        System.out.println("Wins                \t: " + monster.getWins());
        if (monster.getElement().isEmpty()) {
            System.out.println("Element       \t: None");
        } else {
            System.out.println("Element          \t: " + monster.getElement().get(0).getNama());
        }
        System.out.println("Evolved              \t: " + (monster.hasEvolved() ? "Yes" : "No"));
    }
}

class GameActionException extends Exception {
    public GameActionException(String message) {
        super(message);
    }
}

class LevelOutOfBoundsException extends Exception {
    public LevelOutOfBoundsException(String message) {
        super(message);
    }
}

