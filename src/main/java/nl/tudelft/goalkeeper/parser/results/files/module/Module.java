package nl.tudelft.goalkeeper.parser.results.files.module;

import java.util.List;

/**
 * Interface for modules.
 */
public interface Module {
    /**
     * Get a list of all rules in the module.
     * @return List of all rules in the module.
     */
    List<ModuleRule> getRules();

    /**
     * Adds a rule to the module.
     * @param rule ModuleRule to be added.
     */
    void addRule(ModuleRule rule);
}
