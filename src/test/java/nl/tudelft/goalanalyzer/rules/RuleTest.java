package nl.tudelft.goalanalyzer.rules;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the Rule class.
 */
@SuppressWarnings("magicnumber")
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
        assertThat(rule.addStage(s1)).isEqualTo(rule);
        assertThat(rule.getStages().size()).isEqualTo(1);
        rule.addStage(s1);
        assertThat(rule.getStages().size()).isEqualTo(1);
        rule.addStage(s2);
        assertThat(rule.getStages().size()).isEqualTo(2);
        assertThat(rule.getStages()).contains(s1).contains(s2);
    }

    /**
     * Checks that the right severity is detected.
     */
    @Test
    void severityOfTest() {
        assertThat(rule.severityOf(5)).isEqualTo(0);
        Stage s1 = new Stage().setSeverity(0).setMin(0).setMax(3);
        Stage s2 = new Stage().setSeverity(1).setMin(3).setMax(5);
        Stage s3 = new Stage().setSeverity(2).setMin(5);
        rule.addStage(s1).addStage(s2).addStage(s3);
        assertThat(rule.severityOf(5)).isEqualTo(2);
    }
}
