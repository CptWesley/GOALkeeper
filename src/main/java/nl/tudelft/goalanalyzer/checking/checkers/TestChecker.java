package nl.tudelft.goalanalyzer.checking.checkers;

import nl.tudelft.goalanalyzer.checking.Checker;
import nl.tudelft.goalanalyzer.checking.violations.Violation;
import nl.tudelft.goalanalyzer.rules.RuleSet;

import java.util.Collection;

@Checker
public class TestChecker implements CheckerInterface {
    @Override
    public Collection<Violation> run(String[] files, RuleSet ruleSet) {


        return null;
    }
}
