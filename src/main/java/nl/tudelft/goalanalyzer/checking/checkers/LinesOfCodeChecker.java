package nl.tudelft.goalanalyzer.checking.checkers;

import nl.tudelft.goalanalyzer.checking.Checker;
import nl.tudelft.goalanalyzer.checking.violations.Violation;
import nl.tudelft.goalanalyzer.rules.Rule;
import nl.tudelft.goalanalyzer.rules.RuleSet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
    public Collection<Violation> run(String[] files, RuleSet ruleSet) {
        List<Violation> violations = new ArrayList<>();
        if (!isEnabled(ruleSet)) {
            return violations;
        }
        Rule rule = ruleSet.getRule(RULE_NAME);
        for (String fileName : files) {
            int lines;
            try {
                lines = countLines(fileName);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
            int severity = rule.severityOf(lines);
            if (severity > 0) {
                boolean error = severity >= ruleSet.getErrorSeverity();
                violations.add(new Violation(VIOLATION_NAME, severity)
                        .setActualValue(lines)
                        .setMaximumValue(rule.maxValueBefore(1))
                        .setFile(fileName)
                        .setError(error));
            }
        }
        return violations;
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

    /**
     * Count the number of lines in a file.
     * @param fileName File to check for.
     * @return Number of lines.
     */
    private int countLines(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(fileName), "UTF-8"));
        int lines = 0;
        while (reader.readLine() != null) {
            ++lines;
        }
        reader.close();
        return lines;
    }
}
