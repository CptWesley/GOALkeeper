package nl.tudelft.goalanalyzer.checking.violations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the Violation class.
 */
class ViolationTest {
    private Violation violation;

    /**
     * Sets up the testing environment before each test.
     */
    @BeforeEach
    void setup() {
        violation = new Violation("blah", 3);
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
        assertThat(violation.getStartingLine()).isEqualTo(-1);
        assertThat(violation.getEndingLine()).isEqualTo(-1);
        assertThat(violation.getFile()).isEqualTo("");
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
        assertThat(violation.setStartingLine(12)).isSameAs(violation);
        assertThat(violation.getStartingLine()).isEqualTo(12);
        assertThat(violation.setEndingLine(33)).isSameAs(violation);
        assertThat(violation.getEndingLine()).isEqualTo(33);
        assertThat(violation.setFile("sgffsgdfgds")).isSameAs(violation);
        assertThat(violation.getFile()).isEqualTo("sgffsgdfgds");
    }

    /**
     * Checks in an extremely smokey test that the toString method is correct.
     */
    @Test
    void toStringTest() {
        assertThat(violation.toString())
                .isEqualTo("'blah' of severity 3 found.");
        assertThat(violation.setFile("hithere").toString())
                .isEqualTo("'blah' of severity 3 found in 'hithere'.");
        assertThat(violation.setFile("hithere").toString())
                .isEqualTo("'blah' of severity 3 found in 'hithere'.");
        assertThat(violation.setStartingLine(4).toString())
                .isEqualTo("'blah' of severity 3 found in 'hithere'.");
        assertThat(violation.setEndingLine(4).toString())
                .isEqualTo("'blah' of severity 3 found in 'hithere' at line 4.");
        assertThat(violation.setEndingLine(5).toString())
                .isEqualTo("'blah' of severity 3 found in 'hithere' at lines 4-5.");
        assertThat(violation.setActualValue(20).toString())
                .isEqualTo("'blah' of severity 3 found in 'hithere' at lines 4-5.");
        assertThat(violation.setMaximumValue(30).toString())
                .isEqualTo("'blah' of severity 3 found in 'hithere' " +
                        "at lines 4-5. Value was: '20.0' while maximum is '30.0'.");
    }
}
