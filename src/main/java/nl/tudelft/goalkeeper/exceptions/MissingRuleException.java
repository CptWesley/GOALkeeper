package nl.tudelft.goalkeeper.exceptions;

/**
 * Class for exception thrown when a specified rule or property is missing.
 */
public class MissingRuleException extends Exception {

    /**
     * Constructor for a messageless instance of the MissingRuleException.
     */
    public MissingRuleException() {
        super();
    }

    /**
     * Constructor for an instance with a message of the MissingRuleException.
     * @param message Message of the exception.
     */
    public MissingRuleException(String message) {
        super(message);
    }
}
