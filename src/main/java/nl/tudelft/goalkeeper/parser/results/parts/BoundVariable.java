package nl.tudelft.goalkeeper.parser.results.parts;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Implementation of Variable for bound variables.
 */
@EqualsAndHashCode
public class BoundVariable implements Variable {
    @Getter private String identifier;

    /**
     * Creates a new bound variable instance.
     * @param identifier Identifier of the variable.
     */
    public BoundVariable(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return identifier;
    }
}
