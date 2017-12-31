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
        return new GoalCondition();
    }

    /**
     * {@inheritDoc}
     */
    @BeforeEach
    void setup() {
        condition = new GoalCondition();
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
        GoalCondition other = new GoalCondition();
        assertThat(condition).isEqualTo(other);
        assertThat(condition.hashCode()).isEqualTo(other.hashCode());
        Expression e = Mockito.mock(Expression.class);
        condition.addExpression(e);
        other.addExpression(e);
        assertThat(condition).isEqualTo(other);
        assertThat(condition.hashCode()).isEqualTo(other.hashCode());
    }

    /**
     * Checks that toString is implemented correctly.
     */
    @Test
    void toStringTest() {
        assertThat(condition.toString()).isEqualTo("goal()");
        Expression e1 = Mockito.mock(Expression.class);
        Expression e2 = Mockito.mock(Expression.class);
        Mockito.when(e1.toString()).thenReturn("raf1");
        Mockito.when(e2.toString()).thenReturn("raf2");
        assertThat(condition.addExpression(e1).toString()).isEqualTo("goal(raf1)");
        assertThat(condition.addExpression(e2).toString()).isEqualTo("goal(raf1, raf2)");
    }
}
