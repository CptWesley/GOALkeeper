package nl.tudelft.goalanalyzer.exceptions;

/**
 * Class for exception thrown when a file is of the wrong type.
 */
public class WrongFileTypeException extends Exception {

    /**
     * Constructor for a messageless instance of the WrongFileTypeException.
     */
    public WrongFileTypeException() {
        super();
    }

    /**
     * Constructor for an instance with a message of the WrongFileTypeException.
     * @param message Message of the exception.
     */
    public WrongFileTypeException(String message) {
        super(message);
    }
}
