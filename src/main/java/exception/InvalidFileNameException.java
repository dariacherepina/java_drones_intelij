package exception;

/**
 * @author Daria Cherepina
 */
public class InvalidFileNameException extends java.lang.Exception {
    /**
     * Throw when the filename is not valid
     *
     * @param message String
     */
    public InvalidFileNameException(String message) {
        super(message);
    }
}
