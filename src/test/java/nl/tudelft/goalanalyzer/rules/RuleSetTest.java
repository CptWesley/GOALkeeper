package nl.tudelft.goalanalyzer.rules;

import nl.tudelft.goalanalyzer.exceptions.MalformedRulesException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the RuleSet class.
 */
class RuleSetTest {

    private RuleSet set;

    /**
     * Sets up the testing environment before each test.
     */
    @BeforeEach
    void setup() throws MalformedRulesException, IOException, URISyntaxException {
        URL resource = this.getClass().getClassLoader().getResource("rules.json");
        String file = Paths.get(resource.toURI()).toFile().getAbsolutePath();
        set = RuleSet.load(file);
    }

    /**
     * Checks if the default severity is correct.
     */
    @Test
    void severityDefaultTest() {
        assertThat(set.getErrorSeverity()).isEqualTo(2);
    }

    /**
     * Checks if we have a rule.
     */
    @Test
    void hasRuleTrueTest() {
        assertThat(set.hasRule("LOC")).isTrue();
    }

    /**
     * Checks if we have a rule.
     */
    @Test
    void hasRuleFalseTest() {
        assertThat(set.hasRule("LOC2")).isFalse();
    }

    /**
     * Checks if we correctly retrieve if we need to fail a build on error.
     */
    @Test
    void failsOnErrorTest() {
        assertThat(set.failsOnError()).isTrue();
    }
}
