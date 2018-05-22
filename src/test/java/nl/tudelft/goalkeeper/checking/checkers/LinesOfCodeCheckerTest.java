package nl.tudelft.goalkeeper.checking.checkers;

import nl.tudelft.goalkeeper.checking.violations.Violation;
import nl.tudelft.goalkeeper.checking.violations.source.FileSource;
import nl.tudelft.goalkeeper.exceptions.MalformedRulesException;
import nl.tudelft.goalkeeper.parser.results.ParseResult;
import nl.tudelft.goalkeeper.parser.results.files.module.ModuleFile;
import nl.tudelft.goalkeeper.rules.RuleSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the LinesOfCodeChecker class.
 */
class LinesOfCodeCheckerTest {
    private static final String SOURCE = "d@$#F^@$%()J";
    private static final int TOO_MANY_LINES = 999;

    private LinesOfCodeChecker checker;
    private ParseResult result;

    /**
     * Sets up the testing environment before each test.
     */
    @BeforeEach
    void setup() {
        checker = new LinesOfCodeChecker();
        result = Mockito.mock(ParseResult.class);
        ModuleFile module = Mockito.mock(ModuleFile.class);
        Mockito.when(module.getContent()).thenReturn(new String[TOO_MANY_LINES]);
        Mockito.when(module.getSource()).thenReturn(new FileSource(SOURCE));
        Mockito.when(result.getActionSpecs()).thenReturn(new ArrayList<>());
        Mockito.when(result.getModules()).thenReturn(Collections.singletonList(module));
    }

    /**
     * Checks that the lines of code test is run correctly.
     * @throws IOException Should not be thrown.
     * @throws MalformedRulesException Should not be thrown.
     */
    @Test
    void runTest() throws IOException, MalformedRulesException {
        Collection<Violation> violations = checker.run(result,
                RuleSet.load("src/test/resources/testfiles/checker-test-rules.json"));
        assertThat(violations.size()).isEqualTo(1);
        Violation violation = (Violation)violations.toArray()[0];
        assertThat(violation.getSource().getFile())
                .isEqualTo(SOURCE);
        assertThat(violation.getSeverity()).isEqualTo(1);
        assertThat(violation.getMaximumValue()).isEqualTo(2);
        assertThat(violation.getActualValue()).isEqualTo(TOO_MANY_LINES);
    }

    /**
     * Checks that the lines of code violations is empty if no rules.
     * @throws IOException Should not be thrown.
     * @throws MalformedRulesException Should not be thrown.
     */
    @Test
    void runTestEmpty() throws IOException, MalformedRulesException {
        Collection<Violation> violations = checker.run(result,
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
        Collection<Violation> violations = checker.run(result,
                RuleSet.load("src/test/resources/testfiles/checker-test-rules-disabled.json"));
        assertThat(violations).isEmpty();
    }
}
