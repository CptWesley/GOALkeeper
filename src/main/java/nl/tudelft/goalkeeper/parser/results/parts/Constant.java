package nl.tudelft.goalkeeper.parser.results.parts;

/**
 * Implementation of Variable for bound variables.
 */
public class Constant extends Parameter {

    private static final int HASH_MODIFIER = 9;

    /**
     * Creates a new bound variable instance.
     * @param identifier Identifier of the variable.
     */
    public Constant(String identifier) {
        super(identifier);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Constant) {
            Constant that = (Constant) o;
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
