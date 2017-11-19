package nl.tudelft.goalanalyzer.checking.checkers;

import nl.tudelft.goalanalyzer.checking.Checker;
import nl.tudelft.goalanalyzer.checking.violations.Violation;
import nl.tudelft.goalanalyzer.rules.RuleSet;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Class that checks the length of files.
 */
@Checker
public final class LinesOfCodeChecker implements CheckerInterface {

    @Override
    public Collection<Violation> run(String[] files, RuleSet ruleSet) {
        return new ArrayList<>();
    }
}
