package nl.tudelft.goalanalyzer.exceptions;

/**
 * Class for exception thrown when a specified exception is not known to the program.
 */
public class MalformedRulesException extends Exception {

    /**
     * Constructor for a messageless instance of the MalformedRulesException.
     */
    public MalformedRulesException() {
        super();
    }

    /**
     * Constructor for an instance with a message of the MalformedRulesException.
     * @param message Message of the exception.
     */
    public MalformedRulesException(String message) {
        super(message);
    }
}
