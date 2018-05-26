package nl.tudelft.goalkeeper.exceptions;

import org.junit.jupiter.api.BeforeEach;

/**
 * Test class for the InvalidKRLanguageException class.
 */
class InvalidKRLanguageExceptionTest extends ExceptionTest {

    private InvalidKRLanguageException exception;

    /**
     * Sets up testing environment before each test.
     */
    @BeforeEach
    void setup() {
        exception = new InvalidKRLanguageException("test123");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    Exception getException() {
        return exception;
    }
}
