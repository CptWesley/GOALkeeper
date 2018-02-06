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
        return new PerceptCondition(Mockito.mock(Expression.class));
    }

    /**
     * {@inheritDoc}
     */
    @BeforeEach
    void setup() {
        expression = Mockito.mock(Expression.class);
        Mockito.when(expression.toString()).thenReturn("raf1");
        condition = new PerceptCondition(expression);
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
        PerceptCondition other = new PerceptCondition(expression);
        assertThat(condition).isEqualTo(other);
        assertThat(condition.hashCode()).isEqualTo(other.hashCode());
    }

    /**
     * Checks that toString is implemented correctly.
     */
    @Test
    void toStringTest() {
        assertThat(condition.toString()).isEqualTo("percept(raf1)");
    }
}
