import java.io.*;
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
        File saveFile = new File(SAVE_FILE_PATH);
        if (!saveFile.exists()) {
            System.out.println("No saved game progress found.");
            return null;
        }
    
        try (BufferedReader reader = new BufferedReader(new FileReader(SAVE_FILE_PATH))) {
            String line;
            String name = null;
            int level = 0;
            int expPoints = 0;
            int healthPoints = 0;
            int wins = 0;
            String elementName = null;
            boolean evolved = false;
    
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Name")) {
                    name = line.split(":")[1].trim();
                } else if (line.startsWith("Level")) {
                    level = Integer.parseInt(line.split(":")[1].trim());
                } else if (line.startsWith("Experience Points")) {
                    expPoints = Integer.parseInt(line.split(":")[1].trim());
                } else if (line.startsWith("Health Points")) {
                    healthPoints = Integer.parseInt(line.split(":")[1].trim());
                } else if (line.startsWith("Wins")) {
                    wins = Integer.parseInt(line.split(":")[1].trim());
                } else if (line.startsWith("Element")) {
                    elementName = line.split(":")[1].trim();
                } else if (line.startsWith("Evolved")) {
                    evolved = Boolean.parseBoolean(line.split(":")[1].trim());
                }
            }
    
            Element element = elementName.equals("None") ? null : new Element(elementName);
            if (element != null) {
                switch (elementName.toUpperCase()) {
                    case "FIRE":
                        element = Element.FIRE;
                        break;
                    case "WATER":
                        element = Element.WATER;
                        break;
                    case "EARTH":
                        element = Element.EARTH;
                        break;
                    case "WIND":
                        element = Element.WIND;
                        break;
                    case "ICE":
                        element = Element.ICE;
                        break;
                    default:
                        element = new Element(elementName); // Initialize with new Element
                }
            }
    
            List<Element> elements = element == null ? new ArrayList<>() : List.of(element);
    
            Player player = new Player("LoadedPlayer"); // Set player with default or required name
            PlayerMonster playerMonster = new PlayerMonster(name, level, elements, player);
            playerMonster.setExpPoint(expPoints);
            playerMonster.setHealthPoint(healthPoints);
            playerMonster.setWins(wins);
            playerMonster.setHasEvolved(evolved);
    
            System.out.println("Game progress loaded successfully.");
            return playerMonster;
    
        } catch (IOException e) {
            System.out.println("Error loading game progress: " + e.getMessage());
            return null;
        }
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
