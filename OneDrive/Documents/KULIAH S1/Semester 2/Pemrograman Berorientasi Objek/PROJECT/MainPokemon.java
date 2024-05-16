import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MainPokemon {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            System.out.println("Welcome to Pokemon World!");
            System.out.print("Enter your player name: ");
            String playerName = scanner.nextLine();

            Player player = new Player(playerName);
            System.out.println("Player " + playerName + " starts the adventure!");

            PlayerMonster playerMonster = new PlayerMonster("Your Monster", 1, List.of(Element.AIR), player);

            HomeBase homeBase = new HomeBase();
            BattleArena battleArena = new BattleArena(); // Create BattleArena object and pass HomeBase object
            Dungeon dungeon = new Dungeon(battleArena); // Pass BattleArena object to Dungeon

            boolean gameRunning = true;
            while (gameRunning) {
                System.out.println("\nWhat do you want to do?");
                System.out.println("1. Enter Dungeon");
                System.out.println("2. Enter Home Base");
                System.out.println("3. Exit and Save");

                try {
                    int choice = getNextInt();
                    switch (choice) {
                        case 1:
                            System.out.println("Welcome to Dungeon");
                            dungeon.explore(playerMonster);
                            break;
                        case 2:
                            homeBase.enterHomeBase(playerMonster);
                            break;
                        case 3:
                            System.out.println("Exiting Pokemon World. Goodbye!");
                            GameProgress.saveProgress(playerMonster); // Save game progress
                            gameRunning = false;
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
}

class GameActionException extends Exception {
    public GameActionException(String message) {
        super(message);
    }
}
