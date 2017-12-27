package nl.tudelft.goalkeeper.parser.results.files.module;

import nl.tudelft.goalkeeper.parser.results.parts.Literal;
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
     * Sets up the testing environment before each test.
     */
    @BeforeEach
    void setup() {
        rule = new Rule(RuleType.IF);
    }

    /**
     * Checks that type is returned correctly.
     */
    @Test
    void getTypeTest() {
        assertThat(rule.getType()).isEqualTo(RuleType.IF);
    }

    /**
     * Checks that actions are handled properly.
     */
    @Test
    void getActionsTest() {
        Literal a = Mockito.mock(Literal.class);
        assertThat(rule.getActions()).isEmpty();
        assertThat(rule.addAction(a)).isEqualTo(rule);
        assertThat(rule.getActions()).hasSize(1);
        assertThat(rule.getActions().get(0)).isSameAs(a);
    }

    /**
     * Checks that conditions are handled properly.
     */
    @Test
    void getConditionsTest() {
        Literal c = Mockito.mock(Literal.class);
        assertThat(rule.getConditions()).isEmpty();
        assertThat(rule.addCondition(c)).isEqualTo(rule);
        assertThat(rule.getConditions()).hasSize(1);
        assertThat(rule.getConditions().get(0)).isSameAs(c);
    }
}
