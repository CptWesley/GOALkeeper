package nl.tudelft.goalanalyzer.exceptions;

import org.junit.jupiter.api.BeforeEach;

/**
 * Test class for the InvalidRuleNameException class.
 */
public class InvalidRuleNameExceptionTest extends ExceptionTest {

    private InvalidRuleNameException exception;

    /**
     * Sets up testing environment before each test.
     */
    @BeforeEach
    void setup() {
        exception = new InvalidRuleNameException("test123");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    Exception getException() {
        return exception;
    }
}
