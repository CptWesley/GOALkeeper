package nl.tudelft.goalkeeper.parser.results.files.module.conditions;

import nl.tudelft.goalkeeper.parser.results.parts.Expression;

/**
 * Class representing belief checks.
 */
public final class BeliefCondition extends Condition {

    private static final int HASH_MODIFIER = 325;
    private static final String TYPE_NAME = "bel";

    /**
     * Creates a new bel condition.
     * @param expression Expression of the condition.
     */
    public BeliefCondition(Expression expression) {
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
