package nl.tudelft.goalkeeper.parser.results.parts;

import lombok.Getter;
import lombok.Setter;
import nl.tudelft.goalkeeper.checking.violations.source.Source;

/**
 * Abstract class for all variable types.
 */
public abstract class Parameter implements Expression, Sourceable {
    @Getter private String identifier;
    @Getter @Setter private Source source;

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
