package nl.tudelft.goalanalyzer.checking.checkers;

import nl.tudelft.goalanalyzer.checking.violations.Violation;
import nl.tudelft.goalanalyzer.rules.RuleSet;

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
