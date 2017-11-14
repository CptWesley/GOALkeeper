package nl.tudelft.goalanalyzer.exceptions;

import org.junit.jupiter.api.BeforeEach;

/**
 * Test class for the NotParsedException class.
 */
public class NotParsedExceptionTest extends ExceptionTest {

    private NotParsedException exception;

    /**
     * Sets up testing environment before each test.
     */
    @BeforeEach
    void setup() {
        exception = new NotParsedException("test123");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    Exception getException() {
        return exception;
    }
}
