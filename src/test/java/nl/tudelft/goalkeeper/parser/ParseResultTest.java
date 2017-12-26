package nl.tudelft.goalkeeper.parser;

import nl.tudelft.goalkeeper.checking.violations.Violation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the ParseResult class.
 */
public class ParseResultTest {

    private ParseResult result;

    /**
     * Sets up test environment before each test.
     */
    @BeforeEach
    void setup() {
        result = new ParseResult();
    }

    /**
     * Checks that setting the successful flag works.
     */
    @Test
    void successfulTest() {
        assertThat(result.isSuccessful()).isFalse();
        result.setSuccessful(true);
        assertThat(result.isSuccessful()).isTrue();
    }

    /**
     * Checks that violations are added successfully.
     */
    @Test
    void violationAdditionTest() {
        assertThat(result.getViolations()).isEmpty();
        Violation violation = Mockito.mock(Violation.class);
        result.addViolation(violation);
        assertThat(result.getViolations()).containsExactly(violation);
    }
}
