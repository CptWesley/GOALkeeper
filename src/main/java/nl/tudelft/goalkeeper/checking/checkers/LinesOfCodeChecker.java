package nl.tudelft.goalkeeper.checking.checkers;

import nl.tudelft.goalkeeper.checking.Checker;
import nl.tudelft.goalkeeper.checking.violations.Violation;
import nl.tudelft.goalkeeper.parser.results.ParseResult;
import nl.tudelft.goalkeeper.parser.results.files.File;
import nl.tudelft.goalkeeper.rules.Rule;
import nl.tudelft.goalkeeper.rules.RuleSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Class that checks the length of files.
 */
@Checker
public final class LinesOfCodeChecker implements CheckerInterface {

    private static final String RULE_NAME = "LOC";
    private static final String VIOLATION_NAME = "Too Many Lines Of Code";

    @Override
    public Collection<Violation> run(ParseResult program, RuleSet ruleSet) {
        List<Violation> violations = new ArrayList<>();
        if (!isEnabled(ruleSet)) {
            return violations;
        }
        for (File file : program.getModules()) {
            createViolations(violations, ruleSet, file);
        }
        for (File file : program.getActionSpecs()) {
            createViolations(violations, ruleSet, file);
        }
        return violations;
    }

    /**
     *
     * @param violations Violations list to add to.
     * @param ruleSet Ruleset to check with.
     * @param file File to check.
     */
    private static void createViolations(List<Violation> violations, RuleSet ruleSet, File file) {
        Rule rule = ruleSet.getRule(RULE_NAME);
        int lines = file.getContent().length;
        int severity = rule.severityOf(lines);
        if (severity > 0) {
            boolean error = severity >= ruleSet.getErrorSeverity();
            violations.add(new Violation(VIOLATION_NAME, severity)
                    .setActualValue(lines)
                    .setMaximumValue(rule.maxValueBefore(1))
                    .setSource(file.getSource())
                    .setError(error));
        }
    }

    /**
     * Checks if this checker should be enabled.
     * @param ruleSet RuleSet to check for.
     * @return True if enabled, false otherwise.
     */
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
