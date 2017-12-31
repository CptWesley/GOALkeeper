package nl.tudelft.goalkeeper.parser.results.files.module.conditions;

import nl.tudelft.goalkeeper.parser.results.parts.Expression;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the AGoalCondition class.
 */
class AGoalConditionTest extends ConditionTest {

    private AGoalCondition condition;

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
        return new AGoalCondition();
    }

    /**
     * {@inheritDoc}
     */
    @BeforeEach
    void setup() {
        condition = new AGoalCondition();
    }

    /**
     * Checks that the hashModifier is correctly implemented.
     */
    @Test
    void hashModifierTest() {
        assertThat(condition.getHashModifier()).isEqualTo(33);
    }

    /**
     * Checks that the typeName getter is correctly implemented.
     */
    @Test
    void typeNameTest() {
        assertThat(condition.getTypeName()).isEqualTo("a-goal");
    }

    /**
     * Checks that equal objects are equal.
     */
    @Test
    void equalsSameTest() {
        AGoalCondition other = new AGoalCondition();
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
        assertThat(condition.toString()).isEqualTo("a-goal()");
        Expression e1 = Mockito.mock(Expression.class);
        Expression e2 = Mockito.mock(Expression.class);
        Mockito.when(e1.toString()).thenReturn("raf1");
        Mockito.when(e2.toString()).thenReturn("raf2");
        assertThat(condition.addExpression(e1).toString()).isEqualTo("a-goal(raf1)");
        assertThat(condition.addExpression(e2).toString()).isEqualTo("a-goal(raf1, raf2)");
    }
}
