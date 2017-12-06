package nl.tudelft.goalanalyzer.exceptions;

import org.junit.jupiter.api.BeforeEach;

/**
 * Test class for the WrongFileTypeException class.
 */
class WrongFileTypeExceptionTest extends ExceptionTest {

    private WrongFileTypeException exception;

    /**
     * Sets up testing environment before each test.
     */
    @BeforeEach
    void setup() {
        exception = new WrongFileTypeException("test123");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    Exception getException() {
        return exception;
    }
}
