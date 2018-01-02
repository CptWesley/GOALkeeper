package nl.tudelft.goalkeeper.parser.results.files.module.conditions;

import nl.tudelft.goalkeeper.parser.results.parts.Expression;

/**
 * Class for all a-goal conditions.
 */
public final class AGoalCondition extends Condition {

    private static final int HASH_MODIFIER = 33;
    private static final String TYPE_NAME = "a-goal";

    /**
     * Creates a new goal-a condition.
     * @param expression Expression of the condition.
     */
    public AGoalCondition(Expression expression) {
        super(expression);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getHashModifier() {
        return HASH_MODIFIER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getTypeName() {
        return TYPE_NAME;
    }
}
