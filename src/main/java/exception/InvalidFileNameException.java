package exception;

/**
 * Throw when the filename is not valid
 *
 * @author Daria Cherepina
 */
public class InvalidFileNameException extends java.lang.Exception {
    public InvalidFileNameException(String message) {
        super(message);
    }
}
