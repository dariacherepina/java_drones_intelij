package Exception;

public class InvalidIdInput extends Exception{
    /**
     * Throw when id input of is out of range
     * @param message String
     */
    public InvalidIdInput(String message) {
        super(message);
    }
}
