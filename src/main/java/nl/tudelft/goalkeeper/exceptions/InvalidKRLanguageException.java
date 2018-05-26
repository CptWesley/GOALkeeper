package nl.tudelft.goalkeeper.exceptions;

/**
 * Class for exception thrown when a we try to do something,
 * but the KR language is not the same type.
 */
public final class InvalidKRLanguageException extends Exception {

    /**
     * Constructor for a messageless instance of the UnknownKRLanguageException.
     */
    public InvalidKRLanguageException() {
        super();
    }

    /**
     * Constructor for an instance with a message of the UnknownKRLanguageException.
     * @param message Message of the exception.
     */
    public InvalidKRLanguageException(String message) {
        super(message);
    }
}
