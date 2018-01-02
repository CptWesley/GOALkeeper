package nl.tudelft.goalkeeper.checking.checkers;

import goal.tools.TestRun;
import goal.tools.errorhandling.exceptions.GOALRunFailedException;
import languageTools.analyzer.FileRegistry;
import languageTools.analyzer.test.TestValidator;
import languageTools.program.test.TestProgram;
import nl.tudelft.goalkeeper.checking.Checker;
import nl.tudelft.goalkeeper.checking.violations.Violation;
import nl.tudelft.goalkeeper.rules.Rule;
import nl.tudelft.goalkeeper.rules.RuleSet;
import nl.tudelft.goalkeeper.util.FileParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Checker
public class TestChecker implements CheckerInterface {

    private static final String RULE_NAME = "TEST";
    private static final String VIOLATION_NAME = "Test file runner";

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

    @Override
    public Collection<Violation> run(String[] files, RuleSet ruleSet) {
        List<Violation> violations = new ArrayList<>();

        if (!isEnabled(ruleSet)) {
            return violations;
        }

        Rule rule = ruleSet.getRule(RULE_NAME);
        List<File> testFiles = FileParser.getTestFiles(files);
        for (File file: testFiles) {
            try {
                FileRegistry fileRegistry = new FileRegistry();
                TestValidator test = new TestValidator(file.getCanonicalPath(), fileRegistry);
                TestProgram program = test.getProgram();
                TestRun run = new TestRun(program, false);
                run.setDebuggerOutput(false);
                run.run(true);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (GOALRunFailedException e) {
                e.printStackTrace();
            }
            //Run goalchecke
            // and parser the percentages to violations.
        }
        return violations;
    }
}
