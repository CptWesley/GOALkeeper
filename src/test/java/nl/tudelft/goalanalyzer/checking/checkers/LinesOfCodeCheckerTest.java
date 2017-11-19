package nl.tudelft.goalanalyzer.checking.checkers;

import nl.tudelft.goalanalyzer.checking.violations.Violation;
import nl.tudelft.goalanalyzer.exceptions.MalformedRulesException;
import nl.tudelft.goalanalyzer.rules.RuleSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the LinesOfCodeChecker class.
 */
class LinesOfCodeCheckerTest {
    private LinesOfCodeChecker checker;

    /**
     * Sets up the testing environment before each test.
     */
    @BeforeEach
    void setup() {
        checker = new LinesOfCodeChecker();
    }

    /**
     * Checks that the lines of code test is run correctly.
     * @throws IOException Should not be thrown.
     * @throws MalformedRulesException Should not be thrown.
     */
    @Test
    void runTest() throws IOException, MalformedRulesException {
        Collection<Violation> violations = checker.run(new String[]{
                "src/test/resources/testfiles/toomanylines.txt"
        }, RuleSet.load("src/test/resources/testfiles/checker-test-rules.json"));
        assertThat(violations.size()).isEqualTo(1);
        Violation violation = (Violation)violations.toArray()[0];
        assertThat(violation.getFile())
                .isEqualTo("src/test/resources/testfiles/toomanylines.txt");
        assertThat(violation.getSeverity()).isEqualTo(1);
        assertThat(violation.getMaximumValue()).isEqualTo(2);
        assertThat(violation.getActualValue()).isEqualTo(5);
    }
}
