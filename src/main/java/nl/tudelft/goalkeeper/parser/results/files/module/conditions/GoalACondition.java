package nl.tudelft.goalkeeper.parser.results.files.module.conditions;

/**
 * Class for all goal-a conditions.
 */
public final class GoalACondition extends Condition {

    private static final int HASH_MODIFIER = 23;
    private static final String TYPE_NAME = "goal-a";

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
