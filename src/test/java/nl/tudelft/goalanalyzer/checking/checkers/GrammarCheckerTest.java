package nl.tudelft.goalanalyzer.checking.checkers;

import nl.tudelft.goalanalyzer.checking.violations.Violation;
import nl.tudelft.goalanalyzer.exceptions.MalformedRulesException;
import nl.tudelft.goalanalyzer.rules.RuleSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.util.Collection;

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
    void runtest() throws IOException, MalformedRulesException {
        Collection<Violation> violations = checker.run(new String[]{
                "src/test/resources/testfiles/failed.mas2g"
        }, RuleSet.load("src/test/resources/testfiles/checker-test-rules.json"));
        System.out.println(violations.toString());
    }
}
