package nl.tudelft.goalkeeper.checking.checkers;

import nl.tudelft.goalkeeper.checking.violations.Violation;
import nl.tudelft.goalkeeper.exceptions.MalformedRulesException;
import nl.tudelft.goalkeeper.rules.RuleSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the LinesOfCodeChecker class.
 */
class LinesOfCodeCheckerTest {
    private static final String TOO_MANY_LINES_FILE = "src/test/resources/testfiles/toomanylines.txt";

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
        Collection<Violation> violations = checker.run(new String[]{ TOO_MANY_LINES_FILE },
                RuleSet.load("src/test/resources/testfiles/checker-test-rules.json"));
        assertThat(violations.size()).isEqualTo(1);
        Violation violation = (Violation)violations.toArray()[0];
        assertThat(violation.getSource().getFile())
                .isEqualTo(TOO_MANY_LINES_FILE);
        assertThat(violation.getSeverity()).isEqualTo(1);
        assertThat(violation.getMaximumValue()).isEqualTo(2);
        assertThat(violation.getActualValue()).isEqualTo(5);
    }

    /**
     * Checks that the lines of code violations is empty if no rules.
     * @throws IOException Should not be thrown.
     * @throws MalformedRulesException Should not be thrown.
     */
    @Test
    void runTestEmpty() throws IOException, MalformedRulesException {
        Collection<Violation> violations = checker.run(new String[]{ TOO_MANY_LINES_FILE },
                RuleSet.load("src/test/resources/testfiles/checker-test-rules-empty.json"));
        assertThat(violations).isEmpty();
    }

    /**
     * Checks that the lines of code violations is empty if disabled.
     * @throws IOException Should not be thrown.
     * @throws MalformedRulesException Should not be thrown.
     */
    @Test
    void runTestDisabled() throws IOException, MalformedRulesException {
        Collection<Violation> violations = checker.run(new String[]{ TOO_MANY_LINES_FILE },
                RuleSet.load("src/test/resources/testfiles/checker-test-rules-disabled.json"));
        assertThat(violations).isEmpty();
    }
}
