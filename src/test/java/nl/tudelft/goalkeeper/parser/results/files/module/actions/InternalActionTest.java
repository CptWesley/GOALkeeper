package nl.tudelft.goalkeeper.parser.results.files.module.actions;

import nl.tudelft.goalkeeper.parser.results.parts.Expression;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the InternalAction class.
 */
class InternalActionTest {

    private static final String STR1 = "sofpmo";
    private static final String STR2 = "adsf";

    private InternalActionType type;
    private InternalAction action;
    private Expression expression;

    /**
     * Sets up the testing environment before each test.
     */
    @BeforeEach
    void setup() {
        expression = Mockito.mock(Expression.class);
        type = Mockito.mock(InternalActionType.class);
        action = new InternalAction(type, expression);
    }

    /**
     * Checks that the expression is returned correctly.
     */
    @Test
    void getExpressionTest() {
        assertThat(action.getExpression()).isSameAs(expression);
    }

    @Test
    void getTypeTest() {
        assertThat(action.getType()).isSameAs(type);
    }

    /**
     * Checks that the identifier is retrieved correctly.
     */
    @Test
    void getIdentifierTest() {
        Mockito.when(type.getIdentifier()).thenReturn(STR1);
        assertThat(action.getIdentifier()).isEqualTo(STR1);
    }

    /**
     * Checks that the toString method works correctly.
     */
    @Test
    void toStringTest() {
        Mockito.when(type.getIdentifier()).thenReturn(STR1);
        Mockito.when(expression.toString()).thenReturn(STR2);
        assertThat(action.toString()).isEqualTo(STR1 + "(" + STR2 + ")");
    }

    /**
     * Checks that an object is regarded equal to itself.
     */
    @Test
    void equalsSelf() {
        assertThat(action).isEqualTo(action);
        assertThat(action.hashCode()).isEqualTo(action.hashCode());
    }

    /**
     * Checks that an equal object is regarded as equal.
     */
    @Test
    void equalsSame() {
        InternalAction other = new InternalAction(type, expression);
        assertThat(action).isEqualTo(other);
        assertThat(action.hashCode()).isEqualTo(other.hashCode());
    }

    /**
     * Checks that an object is not considered equal to null.
     */
    @Test
    void notEqualsNull() {
        assertThat(action).isNotEqualTo(null);
    }

    /**
     * Checks that an object is not equal when it's of a different type.
     */
    @Test
    void notEqualsDifferentObjectType() {
        assertThat(action).isNotEqualTo("");
    }

    /**
     * Checks that an object is not equal when it has a different update type.
     */
    @Test
    void notEqualsDifferentType() {
        InternalAction other = new InternalAction(Mockito.mock(InternalActionType.class), expression);
        assertThat(action).isNotEqualTo(other);
    }

    /**
     * Checks that an object is not equal to an object with different expression.
     */
    @Test
    void notEqualsDifferentExpression() {
        InternalAction other = new InternalAction(type, Mockito.mock(Expression.class));
        assertThat(action).isNotEqualTo(other);
    }
}
