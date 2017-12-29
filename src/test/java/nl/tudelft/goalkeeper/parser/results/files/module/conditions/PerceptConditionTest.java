package nl.tudelft.goalkeeper.parser.results.files.module.conditions;

import nl.tudelft.goalkeeper.parser.results.parts.Expression;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the PerceptCondition class.
 */
class PerceptConditionTest extends ConditionTest {

    private PerceptCondition condition;

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
        return new PerceptCondition();
    }

    /**
     * {@inheritDoc}
     */
    @BeforeEach
    void setup() {
        condition = new PerceptCondition();
    }

    /**
     * Checks that the hashModifier is correctly implemented.
     */
    @Test
    void hashModifierTest() {
        assertThat(condition.getHashModifier()).isEqualTo(132);
    }

    /**
     * Checks that the typeName getter is correctly implemented.
     */
    @Test
    void typeNameTest() {
        assertThat(condition.getTypeName()).isEqualTo("percept");
    }

    /**
     * Checks that equal objects are equal.
     */
    @Test
    void equalsSameTest() {
        PerceptCondition other = new PerceptCondition();
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
        assertThat(condition.toString()).isEqualTo("percept()");
        Expression e1 = Mockito.mock(Expression.class);
        Expression e2 = Mockito.mock(Expression.class);
        Mockito.when(e1.toString()).thenReturn("raf1");
        Mockito.when(e2.toString()).thenReturn("raf2");
        assertThat(condition.addExpression(e1).toString()).isEqualTo("percept(raf1)");
        assertThat(condition.addExpression(e2).toString()).isEqualTo("percept(raf1, raf2)");
    }
}
