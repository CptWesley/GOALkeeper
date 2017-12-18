package nl.tudelft.goalkeeper.checking.checkers;

import nl.tudelft.goalkeeper.checking.violations.Violation;
import nl.tudelft.goalkeeper.rules.RuleSet;

import java.util.Collection;

/**
 * Interface for static checkers.
 */
public interface CheckerInterface {
    /**
     * Runs the checker.
     * @param files Files to check.
     * @param ruleSet RuleSet to run with.
     * @return Array of violations.
     */
    Collection<Violation> run(String[] files, RuleSet ruleSet);
}
