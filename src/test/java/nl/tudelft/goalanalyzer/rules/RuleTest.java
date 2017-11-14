package nl.tudelft.goalanalyzer.rules;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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

    /**
     * Test that stages are added correctly.
     */
    @Test
    void stagesTest() {
        assertThat(rule.getStages().size()).isEqualTo(0);
        Stage s1 = Mockito.mock(Stage.class);
        Stage s2 = Mockito.mock(Stage.class);
        rule.addStage(s1);
        assertThat(rule.getStages().size()).isEqualTo(1);
        rule.addStage(s1);
        assertThat(rule.getStages().size()).isEqualTo(1);
        rule.addStage(s2);
        assertThat(rule.getStages().size()).isEqualTo(2);
        assertThat(rule.getStages()).contains(s1).contains(s2);
    }
}
