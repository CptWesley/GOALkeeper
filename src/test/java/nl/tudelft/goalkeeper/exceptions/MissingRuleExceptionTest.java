package nl.tudelft.goalkeeper.exceptions;

import org.junit.jupiter.api.BeforeEach;

/**
 * Test class for the MissingRuleException class.
 */
class MissingRuleExceptionTest extends ExceptionTest {

    private MissingRuleException exception;

    /**
     * Sets up testing environment before each test.
     */
    @BeforeEach
    void setup() {
        exception = new MissingRuleException("test123");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    Exception getException() {
        return exception;
    }
}
