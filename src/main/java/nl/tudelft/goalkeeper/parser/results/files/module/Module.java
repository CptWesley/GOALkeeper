package nl.tudelft.goalkeeper.parser.results.files.module;

import nl.tudelft.goalkeeper.parser.results.files.module.Rule;

import java.util.List;

/**
 * Interface for modules.
 */
public interface Module {
    /**
     * Get a list of all rules in the module.
     * @return List of all rules in the module.
     */
    List<Rule> getRules();

    /**
     * Adds a rule to the module.
     * @param rule Rule to be added.
     */
    void addRule(Rule rule);
}
