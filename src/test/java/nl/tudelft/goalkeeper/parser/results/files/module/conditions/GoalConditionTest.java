package nl.tudelft.goalkeeper.parser.results.files.module.conditions;

import nl.tudelft.goalkeeper.parser.results.parts.Expression;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the GoalCondition class.
 */
class GoalConditionTest extends ConditionTest {

    private Expression expression;
    private GoalCondition condition;

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
        return new GoalCondition(Mockito.mock(Expression.class));
    }

    /**
     * {@inheritDoc}
     */
    @BeforeEach
    void setup() {
        expression = Mockito.mock(Expression.class);
        Mockito.when(expression.toString()).thenReturn("raf1");
        condition = new GoalCondition(expression);
    }

    /**
     * Checks that the hashModifier is correctly implemented.
     */
    @Test
    void hashModifierTest() {
        assertThat(condition.getHashModifier()).isEqualTo(67);
    }

    /**
     * Checks that the typeName getter is correctly implemented.
     */
    @Test
    void typeNameTest() {
        assertThat(condition.getTypeName()).isEqualTo("goal");
    }

    /**
     * Checks that equal objects are equal.
     */
    @Test
    void equalsSameTest() {
        GoalCondition other = new GoalCondition(expression);
        assertThat(condition).isEqualTo(other);
        assertThat(condition.hashCode()).isEqualTo(other.hashCode());
    }

    /**
     * Checks that toString is implemented correctly.
     */
    @Test
    void toStringTest() {
        assertThat(condition.toString()).isEqualTo("goal(raf1)");
    }
}
