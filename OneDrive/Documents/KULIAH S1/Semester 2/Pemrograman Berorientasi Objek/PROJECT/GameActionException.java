public class GameActionException extends Exception {
    public GameActionException(String message) {
        super(message);
    }
}

class LevelOutOfBoundsException extends Exception {
    public LevelOutOfBoundsException(String message) {
        super(message);
    }
}
