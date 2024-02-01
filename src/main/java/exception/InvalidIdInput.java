package exception;

/**
 * Throw when input of id is out of range
 *
 * @author Daria Cherepina
 */
public class InvalidIdInput extends Exception {
    public InvalidIdInput(String message) {
        super(message);
    }
}
