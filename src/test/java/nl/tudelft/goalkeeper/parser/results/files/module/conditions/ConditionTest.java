package nl.tudelft.goalkeeper.parser.results.files.module.conditions;

import nl.tudelft.goalkeeper.parser.results.parts.Expression;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Abstract test class for Condition instances.
 */
abstract class ConditionTest {
    /**
     * Gets an instance of condition to test on.
     * @return Instance of condition to test on.
     */
    abstract Condition getCondition();

    /**
     * Gets an instance of condition to test on.
     * @return Instance of condition to test on.
     */
    abstract Condition getNewCondition();

    /**
     * Checks that expression addition works correctly.
     */
    @Test
    void addExpressionTest() {
        assertThat(getCondition().getExpressions()).isEmpty();
        Expression e = Mockito.mock(Expression.class);
        assertThat(getCondition().addExpression(e)).isSameAs(getCondition());
        assertThat(getCondition().getExpressions()).hasSize(1);
        assertThat(getCondition().getExpressions()).containsExactly(e);
    }

    /**
     * Checks that an object is equal to itself.
     */
    @Test
    void equalsSelfTest() {
        assertThat(getCondition()).isEqualTo(getCondition());
    }

    /**
     * Checks that an object is not equal to null.
     */
    @Test
    void notEqualsNullTest() {
        assertThat(getCondition()).isNotEqualTo(null);
    }

    /**
     * Checks that an object is not equal to a different typed object.
     */
    @Test
    void notEqualsDifferentTypeTest() {
        assertThat(getCondition()).isNotEqualTo("");
    }

    /**
     * Checks that an object is not equal to a different condition.
     */
    @Test
    void notEqualsDifferentExpressionsTest() {
        Condition other = getNewCondition();
        Expression e1 = Mockito.mock(Expression.class);
        Expression e2 = Mockito.mock(Expression.class);
        other.addExpression(e1);
        assertThat(getCondition()).isNotEqualTo(other);
        getCondition().addExpression(e2);
        assertThat(getCondition()).isNotEqualTo(other);
    }
}
