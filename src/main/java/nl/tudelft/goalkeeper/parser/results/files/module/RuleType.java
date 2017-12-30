package nl.tudelft.goalkeeper.parser.results.files.module;

import lombok.Getter;

/**
 * Enumeration of rule types.
 */
public enum RuleType {
    IF("if", "then"),
    FORALL("forall", "do"),
    LISTALL("listall", "do");

    @Getter private String prefix, suffix;

    /**
     * Creates an instance of the RuleType enum.
     * @param prefix Condition prefix of the rule.
     * @param suffix Condition suffix of the rule.
     */
    RuleType(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }
}
