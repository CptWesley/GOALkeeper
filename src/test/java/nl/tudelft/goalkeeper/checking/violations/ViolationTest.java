package nl.tudelft.goalkeeper.checking.violations;

import nl.tudelft.goalkeeper.checking.violations.source.Source;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the Violation class.
 */
class ViolationTest {
    private Violation violation;
    private Source source;

    /**
     * Sets up the testing environment before each test.
     */
    @BeforeEach
    void setup() {
        violation = new Violation("blah", 3);
        source = Mockito.mock(Source.class);
    }

    /**
     * Checks that the violation is contructed correctly.
     */
    @Test
    void constructorTest() {
        assertThat(violation.getMessage()).isEqualTo("blah");
        assertThat(violation.getSeverity()).isEqualTo(3);
        assertThat(violation.getActualValue()).isEqualTo(-1);
        assertThat(violation.getMaximumValue()).isEqualTo(-1);
        assertThat(violation.getSource()).isNull();
        assertThat(violation.isError()).isFalse();
    }

    /**
     * Smoke test for all data values.
     */
    @Test
    void dataTest() {
        assertThat(violation.setActualValue(5)).isSameAs(violation);
        assertThat(violation.getActualValue()).isEqualTo(5);
        assertThat(violation.setMaximumValue(7)).isSameAs(violation);
        assertThat(violation.getMaximumValue()).isEqualTo(7);
        assertThat(violation.setSource(source)).isEqualTo(violation);
        assertThat(violation.getSource()).isSameAs(source);
        assertThat(violation.setError(true)).isSameAs(violation);
        assertThat(violation.isError()).isTrue();
    }

    /**
     * Checks in an extremely smokey test that the toString method is correct.
     */
    @Test
    void toStringTest() {
        Mockito.when(source.toString()).thenReturn("in 'hithere' at lines 4-5");
        assertThat(violation.toString())
                .isEqualTo("Warning 'blah' of severity 3 found.");
        assertThat(violation.setError(true).toString())
                .isEqualTo("Error 'blah' of severity 3 found.");
        assertThat(violation.setSource(source).toString())
                .isEqualTo("Error 'blah' of severity 3 found in 'hithere' at lines 4-5.");
        assertThat(violation.setActualValue(20).toString())
                .isEqualTo("Error 'blah' of severity 3 found in 'hithere' at lines 4-5.");
        assertThat(violation.setMaximumValue(30).toString())
                .isEqualTo("Error 'blah' of severity 3 found in 'hithere' " +
                        "at lines 4-5. Value was: '20.0' while maximum is '30.0'.");
    }
}
