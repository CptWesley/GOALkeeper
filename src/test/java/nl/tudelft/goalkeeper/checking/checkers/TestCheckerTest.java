package nl.tudelft.goalkeeper.checking.checkers;

import nl.tudelft.goalkeeper.checking.violations.Violation;
import nl.tudelft.goalkeeper.exceptions.MalformedRulesException;
import nl.tudelft.goalkeeper.rules.RuleSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

class TestCheckerTest {
    private static final String TEST2G_FILE =
            "src/test/resources/testfiles/failedtest/newOperators.test2g";

    private TestChecker checker;

    @BeforeEach
    void init() {
        checker = new TestChecker();
    }

    @Test
    void runTest() throws IOException, MalformedRulesException {
        Collection<Violation> violations = checker.run(new String[] {TEST2G_FILE},
                RuleSet.load("src/test/resources/testfiles/checker-test-rules.json"));
        assertThat(violations.size()).isEqualTo(1);
    }

}
