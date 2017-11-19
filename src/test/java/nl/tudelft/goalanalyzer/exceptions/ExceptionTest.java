package nl.tudelft.goalanalyzer.exceptions;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Abstract class to test custom exceptions.
 */
abstract class ExceptionTest {

    /**
     * Get an exception instance.
     * @return exception instance of to be tested exception.
     */
    abstract Exception getException();

    /**
     * Tests that the message is correctly passed to the super class.
     */
    @Test
    void messageTest() {
        assertThat(getException().getMessage()).isEqualTo("test123");
    }
}
