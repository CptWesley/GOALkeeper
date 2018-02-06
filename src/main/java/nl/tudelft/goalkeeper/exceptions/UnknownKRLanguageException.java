package nl.tudelft.goalkeeper.exceptions;

/**
 * Class for exception thrown when a we try to do something, but the KR language is not known
 * or is not implemented yet.
 */
public final class UnknownKRLanguageException extends Exception {

    /**
     * Constructor for a messageless instance of the UnknownKRLanguageException.
     */
    public UnknownKRLanguageException() {
        super();
    }

    /**
     * Constructor for an instance with a message of the UnknownKRLanguageException.
     * @param message Message of the exception.
     */
    public UnknownKRLanguageException(String message) {
        super(message);
    }
}
