package nl.tudelft.goalkeeper.parser.results.files.module.conditions;

import nl.tudelft.goalkeeper.parser.results.parts.Expression;
import org.junit.jupiter.api.Test;

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
     * Checks that expression works correctly.
     */
    @Test
    void getExpressionTest() {
        assertThat(getCondition().getExpression()).isNotNull();
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
        assertThat(getCondition()).isNotEqualTo(getNewCondition());
    }
}
