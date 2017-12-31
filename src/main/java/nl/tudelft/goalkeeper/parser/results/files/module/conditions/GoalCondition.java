package nl.tudelft.goalkeeper.parser.results.files.module.conditions;

/**
 * Class for all goal conditions.
 */
public final class GoalCondition extends Condition {

    private static final int HASH_MODIFIER = 67;
    private static final String TYPE_NAME = "goal";

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
