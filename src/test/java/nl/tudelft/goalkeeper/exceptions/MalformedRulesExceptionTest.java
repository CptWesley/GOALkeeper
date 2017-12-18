package nl.tudelft.goalkeeper.exceptions;

import org.junit.jupiter.api.BeforeEach;

/**
 * Test class for the MalformedRulesException class.
 */
class MalformedRulesExceptionTest extends ExceptionTest {

    private MalformedRulesException exception;

    /**
     * Sets up testing environment before each test.
     */
    @BeforeEach
    void setup() {
        exception = new MalformedRulesException("test123");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    Exception getException() {
        return exception;
    }
}
