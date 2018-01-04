package nl.tudelft.goalkeeper.parser.results.files.module.actions;

import nl.tudelft.goalkeeper.parser.results.parts.Expression;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the StartTimerAction class.
 */
class StartTimerActionTest {

    private StartTimerAction action;
    private Expression name, interval, duration;

    /**
     * Sets up the testing environment before each test.
     */
    @BeforeEach
    void setup() {
        name = Mockito.mock(Expression.class);
        interval = Mockito.mock(Expression.class);
        duration = Mockito.mock(Expression.class);
        action = new StartTimerAction(name, interval, duration);
    }

    /**
     * Checks that the type is returned correctly.
     */
    @Test
    void getTypeTest() {
        assertThat(action.getType()).isSameAs(InternalActionType.START_TIMER);
    }

    /**
     * Checks that the expression is returned correctly.
     */
    @Test
    void getExpressionTest() {
        assertThat(action.getExpression()).isSameAs(name);
    }

    /**
     * Checks that the interval is returned correctly.
     */
    @Test
    void getIntervalTest() {
        assertThat(action.getInterval()).isSameAs(interval);
    }

    /**
     * Checks that the duration is returned correctly.
     */
    @Test
    void getDurationTest() {
        assertThat(action.getDuration()).isSameAs(duration);
    }

    /**
     * Checks that the toString result is returned correctly.
     */
    @Test
    void toStringTest() {
        Mockito.when(name.toString()).thenReturn("a");
        Mockito.when(interval.toString()).thenReturn("b");
        Mockito.when(duration.toString()).thenReturn("c");
        assertThat(action.toString()).isEqualTo("starttimer/3(a, b, c)");
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
        StartTimerAction other = new StartTimerAction(name, interval, duration);
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
     * Checks that an object is not equal to an object with a different expression.
     */
    @Test
    void notEqualsDifferentExpression() {
        StartTimerAction other = new StartTimerAction(Mockito.mock(Expression.class), interval, duration);
        assertThat(action).isNotEqualTo(other);
    }

    /**
     * Checks that an object is not equal to an object with a different interval.
     */
    @Test
    void notEqualsDifferentInterval() {
        StartTimerAction other = new StartTimerAction(name, Mockito.mock(Expression.class), duration);
        assertThat(action).isNotEqualTo(other);
    }

    /**
     * Checks that an object is not equal to an object with a different duration.
     */
    @Test
    void notEqualsDifferentDuration() {
        StartTimerAction other = new StartTimerAction(name, interval, Mockito.mock(Expression.class));
        assertThat(action).isNotEqualTo(other);
    }
}
