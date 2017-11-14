package nl.tudelft.goalanalyzer.exceptions;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the InvalidRuleNameException class.
 */
public class InvalidRuleNameExceptionTest {

    /**
     * Tests that the message is correctly passed to the super class.
     */
    @Test
    void messageTest() {
        InvalidRuleNameException exception = new InvalidRuleNameException("test123");
        assertThat(exception.getMessage()).isEqualTo("test123");
    }
}
