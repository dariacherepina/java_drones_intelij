package Exception;

public class InvalidIdInput extends Exception {
    /**
     * Throw when input of id is out of range
     *
     * @param message String
     */
    public InvalidIdInput(String message) {
        super(message);
    }
}