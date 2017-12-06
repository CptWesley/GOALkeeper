package nl.tudelft.goalanalyzer.checking.checkers;

import nl.tudelft.goalanalyzer.checking.violations.Violation;
import nl.tudelft.goalanalyzer.exceptions.MalformedRulesException;
import nl.tudelft.goalanalyzer.rules.RuleSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.util.Collection;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Created by Cedric Willekens (4530373) on 12/6/2017.
 */
public class GrammarCheckerTest {
    private CheckerInterface checker;

    @BeforeEach
    void setup() {
        this.checker = new GrammarChecker();
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
