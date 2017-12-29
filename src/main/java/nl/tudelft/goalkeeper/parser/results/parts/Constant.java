package nl.tudelft.goalkeeper.parser.results.parts;

/**
 * Implementation of Variable for bound variables.
 */
public class Constant extends Parameter {
    /**
     * Creates a new bound variable instance.
     * @param identifier Identifier of the variable.
     */
    public Constant(String identifier) {
        super(identifier);
    }
}
