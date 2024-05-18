import java.io.*;
import java.util.List;
import java.util.*;

public class GameProgress {
    private static final String SAVE_FILE_PATH = "game_progress.txt";

    public static void saveProgress(PlayerMonster playerMonster) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SAVE_FILE_PATH))) {
            writer.write("========== GAME PROGRESS ==========\n");
            writer.write("Name              \t: " + playerMonster.getNama() + "\n");
            writer.write("Level             \t: " + playerMonster.getLevel() + "\n");
            writer.write("Experience Points \t: " + playerMonster.getExpPoint() + "\n");
            writer.write("Health Points     \t: " + playerMonster.getHealthPoint() + "\n");
            writer.write("Wins              \t: " + playerMonster.getWins() + "\n"); // Save wins
             // Save the element
             if (playerMonster.getElement().isEmpty() || playerMonster.getElement().get(0) == null) {
                writer.write("Element        \t: None\n");
            } else {
                writer.write("Element            \t: " + playerMonster.getElement().get(0).getNama() + "\n");
            }

            // Save the evolved status
            writer.write("Evolved           \t: " + playerMonster.hasEvolved() + "\n");
            writer.write("===================================\n");
            System.out.println("Game progress saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving game progress: " + e.getMessage());
        }
    }

    public static PlayerMonster loadProgress() throws LevelOutOfBoundsException {
        PlayerMonster playerMonster = new PlayerMonster();
        try (BufferedReader reader = new BufferedReader(new FileReader(SAVE_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Name              \t: ")) {
                    String name = line.substring(24).trim();
                    playerMonster.setNama(name);
                } else if (line.startsWith("Level             \t: ")) {
                    int level = Integer.parseInt(line.substring(24).trim());
                    playerMonster.setLevel(level);
                } else if (line.startsWith("Experience Points \t: ")) {
                    String[] parts = line.split(":\\s+");
                    int expPoints = Integer.parseInt(parts[1].trim());
                    playerMonster.setExpPoint(expPoints);
                } else if (line.startsWith("Health Points     \t: ")) {
                    int healthPoints = Integer.parseInt(line.substring(24).trim());
                    playerMonster.setHealthPoint(healthPoints);
                } else if (line.startsWith("Wins              \t: ")) {
                    int wins = Integer.parseInt(line.substring(24).trim());
                    playerMonster.setWins(wins);
                } else if (line.startsWith("Evolved           \t: ")) {
                    boolean evolved = Boolean.parseBoolean(line.substring(24).trim());
                    playerMonster.setHasEvolved(evolved);
                } else if (line.startsWith("Element           \t: ")) {
                    String elementName = line.substring(24).trim();
                    if (!elementName.equals("None")) {
                        Element element = Element.fromString(elementName);
                        playerMonster.setElement(List.of(element));
                    }
                }
            }
            System.out.println("Game progress loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading game progress: " + e.getMessage());
            return null;
        }
        return playerMonster;
    }


    public static void deleteProgress() {
        File saveFile = new File(SAVE_FILE_PATH);
        if (saveFile.exists()) {
            if (saveFile.delete()) {
                System.out.println("Game progress deleted successfully.");
            } else {
                System.out.println("Failed to delete game progress.");
            }
        } else {
            System.out.println("No saved game progress found to delete.");
        }
    }
}
