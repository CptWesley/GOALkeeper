package nl.tudelft.goalkeeper.parser.results.files.module.conditions;

import nl.tudelft.goalkeeper.parser.results.parts.Expression;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the AGoalCondition class.
 */
class GoalAConditionTest extends ConditionTest {

    private GoalACondition condition;
    private Expression expression;

    /**
     * {@inheritDoc}
     */
    @Override
    Condition getCondition() {
        return condition;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    Condition getNewCondition() {
        return new GoalACondition(Mockito.mock(Expression.class));
    }

    /**
     * {@inheritDoc}
     */
    @BeforeEach
    void setup() {
        expression = Mockito.mock(Expression.class);
        Mockito.when(expression.toString()).thenReturn("raf1");
        condition = new GoalACondition(expression);
    }

    /**
     * Checks that the hashModifier is correctly implemented.
     */
    @Test
    void hashModifierTest() {
        assertThat(condition.getHashModifier()).isEqualTo(23);
    }

    /**
     * Checks that the typeName getter is correctly implemented.
     */
    @Test
    void typeNameTest() {
        assertThat(condition.getTypeName()).isEqualTo("goal-a");
    }

    /**
     * Checks that equal objects are equal.
     */
    @Test
    void equalsSameTest() {
        GoalACondition other = new GoalACondition(expression);
        assertThat(condition).isEqualTo(other);
        assertThat(condition.hashCode()).isEqualTo(other.hashCode());
    }

    /**
     * Checks that toString is implemented correctly.
     */
    @Test
    void toStringTest() {
        assertThat(condition.toString()).isEqualTo("goal-a(raf1)");
    }
}
