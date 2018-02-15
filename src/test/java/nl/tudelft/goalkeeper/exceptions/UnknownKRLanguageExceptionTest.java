package nl.tudelft.goalkeeper.exceptions;

import org.junit.jupiter.api.BeforeEach;

/**
 * Test class for the UnknownKRLanguageException.
 */
class UnknownKRLanguageExceptionTest extends ExceptionTest {

    private UnknownKRLanguageException exception;

    /**
     * Sets up testing environment before each test.
     */
    @BeforeEach
    void setup() {
        exception = new UnknownKRLanguageException("test123");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    Exception getException() {
        return exception;
    }
}
