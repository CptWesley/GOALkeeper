package nl.tudelft.goalkeeper.checking.checkers;

import nl.tudelft.goalkeeper.checking.violations.Violation;
import nl.tudelft.goalkeeper.exceptions.MalformedRulesException;
import nl.tudelft.goalkeeper.rules.RuleSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.util.Collection;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Checker for mas2g files to check if they compile.
 */
public class Mas2GGrammarCheckerTest {
    private CheckerInterface checker;

    @BeforeEach
    void setup() {
        this.checker = new Mas2gGrammarChecker();
    }

    @Test
    void runtestFail() throws IOException, MalformedRulesException {
        Collection<Violation> violations = checker.run(new String[]{
                "src/test/resources/testfiles/failed.mas2g"
        }, RuleSet.load("src/test/resources/testfiles/checker-test-rules.json"));
        assertThat(violations.size()).isGreaterThan(0);
    }

    @Test
    void runTestSucceed() throws IOException, MalformedRulesException {
        Collection<Violation> violations = checker.run(new String[]{
                "src/test/resources/testfiles/bw4t-working/bw4t.mas2g"
        }, RuleSet.load("src/test/resources/testfiles/checker-test-rules.json"));
        assertThat(violations.size()).isEqualTo(0);
    }
}