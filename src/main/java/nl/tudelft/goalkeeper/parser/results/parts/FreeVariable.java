package nl.tudelft.goalkeeper.parser.results.parts;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Implementation of Variable for free variables.
 */
@EqualsAndHashCode
public class FreeVariable implements Variable {
    @Getter private String identifier;

    /**
     * Creates a new free variable instance.
     * @param identifier Identifier of the variable.
     */
    public FreeVariable(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return identifier;
    }
}
