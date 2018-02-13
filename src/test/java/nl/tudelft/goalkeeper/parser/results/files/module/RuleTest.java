package nl.tudelft.goalkeeper.parser.results.files.module;

import nl.tudelft.goalkeeper.parser.results.files.module.actions.Action;
import nl.tudelft.goalkeeper.parser.results.files.module.conditions.AGoalCondition;
import nl.tudelft.goalkeeper.parser.results.files.module.conditions.Condition;
import nl.tudelft.goalkeeper.parser.results.parts.Variable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the Rule class.
 */
@SuppressWarnings("PMD.TooManyMethods")
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
        Action a = Mockito.mock(Action.class);
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
        Condition c = Mockito.mock(Condition.class);
        assertThat(rule.getConditions()).isEmpty();
        assertThat(rule.addCondition(c)).isEqualTo(rule);
        assertThat(rule.getConditions()).hasSize(1);
        assertThat(rule.getConditions().get(0)).isSameAs(c);
    }

    /**
     * Checks that an object is equal to itself.
     */
    @Test
    void equalsSelf() {
        assertThat(rule).isEqualTo(rule);
        assertThat(rule.hashCode()).isEqualTo(rule.hashCode());
    }

    /**
     * Checks that an object is equal to the same object.
     */
    @Test
    void equalsSame() {
        Rule other = new Rule(RuleType.IF);
        assertThat(rule).isEqualTo(other);
        assertThat(rule.hashCode()).isEqualTo(other.hashCode());
    }

    /**
     * Checks that an object is not equal to null.
     */
    @Test
    void notEqualsNull() {
        assertThat(rule).isNotEqualTo(null);
    }

    /**
     * Checks that an object is not equal to an object of a different type.
     */
    @Test
    void notEqualsDifferentType() {
        assertThat(rule).isNotEqualTo("");
    }

    /**
     * Checks that an object is not equal to an object with a different rule type.
     */
    @Test
    void notEqualsDifferentRuleType() {
        Rule other = new Rule(RuleType.FORALL);
        assertThat(rule).isNotEqualTo(other);
    }

    /**
     * Checks that an object is not equal to an object with a different amount of conditions.
     */
    @Test
    void notEqualsDifferentConditionsSize() {
        Rule other = new Rule(RuleType.IF);
        rule.addCondition(Mockito.mock(Condition.class));
        assertThat(rule).isNotEqualTo(other);
    }

    /**
     * Checks that an object is not equal to an object with a different conditions.
     */
    @Test
    void notEqualsDifferentConditions() {
        Rule other = new Rule(RuleType.IF);
        rule.addCondition(Mockito.mock(Condition.class));
        other.addCondition(Mockito.mock(Condition.class));
        assertThat(rule).isNotEqualTo(other);
    }

    /**
     * Checks that an object is not equal to an object with a different amount of actions.
     */
    @Test
    void notEqualsDifferentActionsSize() {
        Rule other = new Rule(RuleType.IF);
        rule.addAction(Mockito.mock(Action.class));
        assertThat(rule).isNotEqualTo(other);
    }

    /**
     * Checks that an object is not equal to an object with a different actions.
     */
    @Test
    void notEqualsDifferentActions() {
        Rule other = new Rule(RuleType.IF);
        rule.addAction(Mockito.mock(Action.class));
        other.addAction(Mockito.mock(Action.class));
        assertThat(rule).isNotEqualTo(other);
    }

    /**
     * Checks that rules with the same conditions return the same conditions hashcode.
     */
    @Test
    void sameConditionsHashCodeTest() {
        Condition c1 = Mockito.mock(Condition.class);
        Condition c2 = Mockito.mock(Condition.class);
        Rule other = new Rule(RuleType.LISTALL);
        rule.addCondition(c1).addCondition(c2);
        other.addCondition(c1).addCondition(c2);
        assertThat(rule.getConditionsHashCode()).isEqualTo(other.getConditionsHashCode());
    }

    /**
     * Checks that rules with the same conditions return the same actions hashcode.
     */
    @Test
    void sameActionsHashCodeTest() {
        Action a1 = Mockito.mock(Action.class);
        Action a2 = Mockito.mock(Action.class);
        Rule other = new Rule(RuleType.LISTALL);
        rule.addAction(a1).addAction(a2);
        other.addAction(a1).addAction(a2);
        assertThat(rule.getActionsHashCode()).isEqualTo(other.getActionsHashCode());
    }

    /**
     * Checks that the toString method returns the correct value.
     */
    @Test
    void emptyToStringTest() {
        assertThat(rule.toString()).isEqualTo("if true then .");
    }

    @Test
    void equivalentTest() {
        Rule rule1 = new Rule(RuleType.IF);
        rule.addCondition(new AGoalCondition(new Variable("test")));
        rule1.addCondition(new AGoalCondition(new Variable("test")));
        assertThat(rule1.equivalent(rule)).isTrue();
    }

    @Test
    void notEquivalentTest() {
        Rule rule1 = new Rule(RuleType.IF);
        rule.addCondition(new AGoalCondition(new Variable("test")));
        rule1.addCondition(new AGoalCondition(new Variable("test2")));
        assertThat(rule1.equivalent(rule)).isFalse();
    }

    /**
     * Checks that the toString method returns the correct value.
     */
    @Test
    void toStringTest() {
        Action a1 = Mockito.mock(Action.class);
        Action a2 = Mockito.mock(Action.class);
        Condition c1 = Mockito.mock(Condition.class);
        Condition c2 = Mockito.mock(Condition.class);
        Mockito.when(a1.toString()).thenReturn("a");
        Mockito.when(a2.toString()).thenReturn("b");
        Mockito.when(c1.toString()).thenReturn("c");
        Mockito.when(c2.toString()).thenReturn("d");
        rule.addAction(a1).addAction(a2);
        rule.addCondition(c1).addCondition(c2);
        assertThat(rule.toString()).isEqualTo("if c, d then a + b.");
    }
}
