package nl.tudelft.goalkeeper.parser.results.files.module.conditions;

/**
 * Class representing belief checks.
 */
public final class BeliefCondition extends Condition {

    private static final int HASH_MODIFIER = 325;
    private static final String TYPE_NAME = "bel";

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
