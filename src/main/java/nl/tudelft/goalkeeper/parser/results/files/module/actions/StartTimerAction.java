package nl.tudelft.goalkeeper.parser.results.files.module.actions;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import nl.tudelft.goalkeeper.parser.results.parts.Expression;

/**
 * Class representing a starttimer/3 action.
 */
@EqualsAndHashCode(callSuper = true)
public final class StartTimerAction extends InternalAction {

    @Getter private Expression interval, duration;

    /**
     * Creates an object instance representing a starttimer/3 action.
     * @param expression Name of the timer.
     * @param interval Interval of the timer.
     * @param duration Duration of the timer.
     */
    public StartTimerAction(Expression expression, Expression interval, Expression duration) {
        super(InternalActionType.START_TIMER, expression);
        this.interval = interval;
        this.duration = duration;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("%s(%s, %s, %s)",
                getIdentifier(),
                getExpression(),
                interval.toString(),
                duration.toString());
    }
}
