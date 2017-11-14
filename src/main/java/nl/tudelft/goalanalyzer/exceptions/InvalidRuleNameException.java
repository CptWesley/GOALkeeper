package nl.tudelft.goalanalyzer.exceptions;

/**
 * Class for exception thrown when a specified exception is not known to the program.
 */
public class InvalidRuleNameException extends Exception {

    /**
     * Constructor for a messageless instance of the InvalidRuleNameException.
     */
    public InvalidRuleNameException() { }

    /**
     * Constructor for an instance with a message of the InvalidRuleNameException.
     * @param message Message of the exception.
     */
    public InvalidRuleNameException(String message) {
        super(message);
    }
}
