package nl.tudelft.goalanalyzer.checking.checkers;

import nl.tudelft.goalanalyzer.checking.Checker;
import nl.tudelft.goalanalyzer.checking.violations.Violation;
import nl.tudelft.goalanalyzer.exceptions.MalformedRulesException;
import nl.tudelft.goalanalyzer.rules.RuleSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collection;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Created by Cedric Willekens (4530373) on 12/6/2017.
 */
public class Act2gGrammarCheckerTest {
    private CheckerInterface checker;
    @BeforeEach
    void init() {
        this.checker = new Act2gGrammarChecker();
    }

    @Test
    void runTestFail() throws IOException, MalformedRulesException {
        Collection<Violation> violations = checker.run(new String[]{
                "src/test/resources/testfiles/failed.act2g"
        }, RuleSet.load("src/test/resources/testfiles/checker-test-rules.json"));
        assertThat(violations.size()).isEqualTo(2);
    }

    @Test
    void runTestSucceed() throws IOException, MalformedRulesException {
        Collection<Violation> violations = checker.run(new String[]{
                "src/test/resources/testfiles/bw4t-working/robot.act2g"
        }, RuleSet.load("src/test/resources/testfiles/checker-test-rules.json"));
        assertThat(violations.size()).isEqualTo(0);
    }

}
