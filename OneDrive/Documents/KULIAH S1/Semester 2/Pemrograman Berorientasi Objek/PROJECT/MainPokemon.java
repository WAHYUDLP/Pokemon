import java.util.List;
import java.util.Scanner;

public class MainPokemon {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    
        System.out.println("Welcome to Pokemon World!");
        System.out.print("Enter your player name: ");
        String playerName = scanner.nextLine();
    
        Player player = new Player(playerName);
        System.out.println("Player " + playerName + " starts the adventure!");
    
        PlayerMonster playerMonster = new PlayerMonster("Your Monster", 1, List.of(Element.AIR), player);
    
        BattleArena battleArena = new BattleArena(playerMonster, null); // Buat objek BattleArena
        Dungeon dungeon = new Dungeon(battleArena); // Melewatkan objek BattleArena ke Dungeon
    
        while (true) {
            System.out.println("\nWhat do you want to do?");
            System.out.println("1. Catch a wild monster");
            System.out.println("2. Enter Home Base");
            System.out.println("3. Exit");
    
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
    
            switch (choice) {
                case 1:
                    dungeon.explore(playerMonster); // Memanggil explore di Dungeon
                    break;
                case 2:
                    // Opsi lain
                    break;
                case 3:
                    System.out.println("Exiting Pokemon World. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please choose again.");
                    break;
            }
        }
    }
    
}
