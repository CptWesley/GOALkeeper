package nl.tudelft.goalkeeper.parser.results.parts;

/**
 * Implementation of Variable for free variables.
 */
public class Variable extends Parameter {

    private static final int HASH_MODIFIER = 21;

    /**
     * Creates a new free variable instance.
     * @param identifier Identifier of the variable.
     */
    public Variable(String identifier) {
        super(identifier);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Variable) {
            Variable that = (Variable) o;
            return this.getIdentifier().equals(that.getIdentifier());
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return getIdentifier().hashCode() ^ HASH_MODIFIER;
    }
}
