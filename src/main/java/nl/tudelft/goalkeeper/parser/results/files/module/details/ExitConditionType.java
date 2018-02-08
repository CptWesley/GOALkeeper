package nl.tudelft.goalkeeper.parser.results.files.module.details;

import lombok.Getter;

/**
 * Exit condition types.
 */
public enum ExitConditionType {
    NOGOALS("nogoals"),
    ALWAYS("always"),
    NEVER("never"),
    NOACTION("noaction");

    @Getter private String name;

    /**
     * Creates an instance of the exit condition types.
     * @param name Name of the condition.
     */
    ExitConditionType(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.name;
    }
}
