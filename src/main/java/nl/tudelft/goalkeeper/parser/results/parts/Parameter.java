package nl.tudelft.goalkeeper.parser.results.parts;

import lombok.Getter;

/**
 * Abstract class for all variable types.
 */
public abstract class Parameter implements Expression {
    @Getter
    private String identifier;

    /**
     * Creates a new free variable instance.
     * @param identifier Identifier of the variable.
     */
    public Parameter(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return identifier;
    }
}
