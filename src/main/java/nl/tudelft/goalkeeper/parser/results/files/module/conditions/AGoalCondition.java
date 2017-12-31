package nl.tudelft.goalkeeper.parser.results.files.module.conditions;

/**
 * Class for all a-goal conditions.
 */
public final class AGoalCondition extends Condition {

    private static final int HASH_MODIFIER = 33;
    private static final String TYPE_NAME = "a-goal";

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
