package nl.tudelft.goalkeeper.parser.results.files.module.conditions;

import nl.tudelft.goalkeeper.parser.results.parts.Expression;

/**
 * Class for all goal conditions.
 */
public final class GoalCondition extends Condition {

    private static final int HASH_MODIFIER = 67;
    private static final String TYPE_NAME = "goal";

    /**
     * Creates a new goal condition.
     * @param expression Expression of the condition.
     */
    public GoalCondition(Expression expression) {
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
