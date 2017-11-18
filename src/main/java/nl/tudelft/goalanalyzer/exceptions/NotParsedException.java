package nl.tudelft.goalanalyzer.exceptions;

/**
 * Class for exception thrown when a parser has not parsed anything yet.
 */
public class NotParsedException extends RuntimeException {

    /**
     * Constructor for a messageless instance of the NotParsedException.
     */
    public NotParsedException() {
        super();
    }

    /**
     * Constructor for an instance with a message of the NotParsedException.
     * @param message Message of the exception.
     */
    public NotParsedException(String message) {
        super(message);
    }
}
