package nl.tudelft.goalkeeper.parser.results.files.module.actions;

import nl.tudelft.goalkeeper.parser.results.parts.Expression;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the ExternalActionTest class.
 */
class ExternalActionTest {

    private static final String SOURCE = "WERGWSDAG";

    private ExternalAction action;
    private Expression arg1, arg2;

    /**
     * Sets up the testing environment before each test.
     */
    @BeforeEach
    void setup() {
        arg1 = Mockito.mock(Expression.class);
        arg2 = Mockito.mock(Expression.class);
        action = new ExternalAction(SOURCE, Arrays.asList(arg1, arg2));
    }

    /**
     * Check if identifier is returned correctly when target is not yet linked.
     */
    @Test
    void getIdentifierTest() {
        assertThat(action.getIdentifier()).isEqualTo("null/2");
    }

    /**
     * Checks that we return the target correctly.
     */
    @Test
    void getTargetTest() {
        assertThat(action.getTarget()).isEqualTo(SOURCE);
    }

    /**
     * Checks that the arguments are returned correctly.
     */
    @Test
    void getArgumentsTest() {
        assertThat(action.getArguments()).containsExactly(arg1, arg2);
    }

    /**
     * Checks that an object is equal to itself.
     */
    @Test
    void equalsSelf() {
        assertThat(action).isEqualTo(action);
        assertThat(action.hashCode()).isEqualTo(action.hashCode());
    }

    /**
     * Checks that an object is equal to the same object.
     */
    @Test
    void equalsSame() {
        ExternalAction other = new ExternalAction(SOURCE, Arrays.asList(arg1, arg2));
        assertThat(action).isEqualTo(other);
        assertThat(action.hashCode()).isEqualTo(other.hashCode());
    }

    /**
     * Checks that an object is not equal to null.
     */
    @Test
    void notEqualsNull() {
        assertThat(action).isNotEqualTo(null);
    }

    /**
     * Checks that an object is not equal to an object of a different type.
     */
    @Test
    void notEqualsDifferentType() {
        assertThat(action).isNotEqualTo("");
    }

    /**
     * Checks that an object is not equal to an object with a different target.
     */
    @Test
    void notEqualsDifferentTarget() {
        ExternalAction other = new ExternalAction("", Arrays.asList(arg1, arg2));
        assertThat(action).isNotEqualTo(other);
    }

    /**
     * Checks that an object is not equal to an object with different amount of arguments.
     */
    @Test
    void notEqualsDifferentArgumentsAmount() {
        ExternalAction other = new ExternalAction(SOURCE, Arrays.asList(arg1));
        assertThat(action).isNotEqualTo(other);
    }

    /**
     * Checks that an object is not equal to an object with different arguments.
     */
    @Test
    void notEqualsDifferentArguments() {
        ExternalAction other = new ExternalAction(SOURCE, Arrays.asList(arg2, arg1));
        assertThat(action).isNotEqualTo(other);
    }

    /**
     * Checks if the toString method has the correct result.
     */
    @Test
    void toStringTest() {
        Mockito.when(arg1.toString()).thenReturn("a");
        Mockito.when(arg2.toString()).thenReturn("b");
        assertThat(action.toString()).isEqualTo("null/2(a, b)");
    }
}
