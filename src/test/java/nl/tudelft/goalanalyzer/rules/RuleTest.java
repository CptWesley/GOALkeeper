package nl.tudelft.goalanalyzer.rules;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the Rule class.
 */
class RuleTest {

    private Rule rule;

    /**
     * Prepares the testing environment before each test.
     */
    @BeforeEach
    void setup() {
        rule = new Rule();
    }

    /**
     * Checks if we can correctly enable and disable rules.
     */
    @Test
    void enabledTest() {
        assertThat(rule.isEnabled()).isFalse();
        rule.setEnabled(true);
        assertThat(rule.isEnabled()).isTrue();
    }
}
