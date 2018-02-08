package nl.tudelft.goalkeeper.parser.results.files.module.details;

import languageTools.program.agent.Module;
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

    /**
     * Gets the proper ExitConditionType based on GOAL ExitCondition.
     * @param condition ExitCondition to convert.
     * @return GOALkeeper ExitConditionType equivalent of the condition.
     */
    public static ExitConditionType get(Module.ExitCondition condition) {
        switch (condition) {
            case NEVER:
                return NEVER;
            case NOGOALS:
                return NOGOALS;
            case NOACTION:
                return NOACTION;
            default:
                return ALWAYS;
        }
    }
}
