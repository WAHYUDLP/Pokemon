import java.io.*;
import java.util.*;

public class GameProgress {
    private static final String SAVE_FILE_PATH = "game_progress.txt";

    public static void saveProgress(PlayerMonster playerMonster) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SAVE_FILE_PATH))) {
            writer.write("========== GAME PROGRESS ==========\n");
            writer.write("Name: " + playerMonster.getNama() + "\n");
            writer.write("Level: " + playerMonster.getLevel() + "\n");
            writer.write("Experience Points: " + playerMonster.getExpPoint() + "\n");
            writer.write("Health Points: " + playerMonster.getHealthPoint() + "\n");
            writer.write("Wins: " + playerMonster.getWins() + "\n"); // Save wins
            writer.write("===================================\n");
            System.out.println("Game progress saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving game progress: " + e.getMessage());
        }
    }

    public static void loadProgress(PlayerMonster playerMonster) {
        try (BufferedReader reader = new BufferedReader(new FileReader(SAVE_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Name: ")) {
                    String name = line.substring(6); // Retrieve player name
                    playerMonster.setNama(name.trim());
                } else if (line.startsWith("Level: ")) {
                    int level = Integer.parseInt(line.substring(7)); // Retrieve level
                    playerMonster.setLevel(level);
                } else if (line.startsWith("Experience Points: ")) {
                    int expPoints = Integer.parseInt(line.substring(19)); // Retrieve experience points
                    playerMonster.setExpPoint(expPoints);
                } else if (line.startsWith("Health Points: ")) {
                    int healthPoints = Integer.parseInt(line.substring(15)); // Retrieve health points
                    playerMonster.setHealthPoint(healthPoints);
                } else if (line.startsWith("Wins: ")) {
                    int wins = Integer.parseInt(line.substring(6)); // Retrieve wins
                    playerMonster.setWins(wins);
                }
            }
            System.out.println("Game progress loaded successfully.");

            // Print the loaded data
            System.out.println("Loaded Player Monster Data:");
            System.out.println("Name: " + playerMonster.getNama());
            System.out.println("Level: " + playerMonster.getLevel());
            System.out.println("Experience Points: " + playerMonster.getExpPoint());
            System.out.println("Health Points: " + playerMonster.getHealthPoint());
            System.out.println("Wins: " + playerMonster.getWins());
        } catch (IOException e) {
            System.out.println("Error loading game progress: " + e.getMessage());
        }
    }

}
