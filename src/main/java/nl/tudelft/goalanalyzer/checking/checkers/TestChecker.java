package nl.tudelft.goalanalyzer.checking.checkers;

import nl.tudelft.goalanalyzer.checking.Checker;
import nl.tudelft.goalanalyzer.checking.violations.Violation;
import nl.tudelft.goalanalyzer.rules.Rule;
import nl.tudelft.goalanalyzer.rules.RuleSet;
import nl.tudelft.goalanalyzer.util.FileParser;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Checker
public class TestChecker implements CheckerInterface {

    private static final String RULE_NAME = "TEST";
    private static final String VIOLATION_NAME = "Test file runner";

    @Override
    public Collection<Violation> run(String[] files, RuleSet ruleSet) {
        List<Violation> violations = new ArrayList<>();

        if (!isEnabled(ruleSet)) {
            return violations;
        }

        Rule rule = ruleSet.getRule(RULE_NAME);
        List<File> testFiles = FileParser.getTestFiles(files);
        for (File file: testFiles) {
            //Run goalchecke
            // and parser the percentages to violations.
        }

        return violations;
    }

    private boolean isEnabled(RuleSet ruleSet) {
        if (!ruleSet.hasRule(RULE_NAME)) {
            return false;
        }
        Rule rule = ruleSet.getRule(RULE_NAME);
        if (!rule.isEnabled()) {
            return false;
        }
        return true;
    }
}
