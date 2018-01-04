package nl.tudelft.goalkeeper.parser.results.files.module.actions;

import lombok.Getter;

/**
 * Enumeration of update types.
 */
public enum InternalActionType {
    INSERT("insert/1"),
    DELETE("delete/1"),
    ADOPT("adopt/1"),
    DROP("drop/1"),
    CANCEL_TIMER("canceltimer/1"),
    START_TIMER("starttimer/3"),
    LOG("log/1"),
    PRINT("print/1"),
    SLEEP("sleep/1"),
    SUBSCRIBE("subscribe/1"),
    UNSUBSCRIBE("unsubscribe/1");

    @Getter private String identifier;

    /**
     * Defines what kind of update action we perform.
     * @param identifier Identifier of the action.
     */
    InternalActionType(String identifier) {
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
