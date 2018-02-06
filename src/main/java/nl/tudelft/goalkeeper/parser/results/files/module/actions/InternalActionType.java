package nl.tudelft.goalkeeper.parser.results.files.module.actions;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

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

    private static Map<String, InternalActionType> lookupMap;
    static {
        lookupMap = new HashMap<>();
        for (InternalActionType type : values()) {
            lookupMap.put(type.identifier, type);
        }
    }

    /**
     * Defines what kind of update action we perform.
     * @param identifier Identifier of the action.
     */
    InternalActionType(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Gets the internal action type corresponding to an identifier.
     * @param identifier Identifier to use.
     * @return Type of the identifier.
     */
    public static InternalActionType get(String identifier) {
        return lookupMap.get(identifier);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getIdentifier();
    }
}
