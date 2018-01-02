package nl.tudelft.goalkeeper.parser.results.files.module.actions;

import lombok.Getter;

/**
 * Enumeration of update types.
 */
public enum UpdateType {
    INSERT("insert/1"),
    DELETE("delete/1"),
    ADOPT("adopt/1"),
    DROP("drop/1");

    @Getter private String identifier;

    /**
     * Defines what kind of update action we perform.
     * @param identifier Identifier of the action.
     */
    UpdateType(String identifier) {
        this.identifier = identifier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getIdentifier();
    }
}
