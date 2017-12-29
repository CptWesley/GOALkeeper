package nl.tudelft.goalkeeper.parser.results.files.module.conditions;

/**
 * Class representing percept checks.
 */
public final class PerceptCondition extends Condition {

    private static final int HASH_MODIFIER = 132;
    private static final String TYPE_NAME = "percept";

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
