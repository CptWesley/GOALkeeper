package nl.tudelft.goalkeeper.checking.checkers;

import goal.tools.TestRun;
import goal.tools.errorhandling.exceptions.GOALRunFailedException;
import groovy.lang.Tuple2;
import languageTools.analyzer.FileRegistry;
import languageTools.analyzer.test.TestValidator;
import languageTools.program.test.TestProgram;
import nl.tudelft.goalkeeper.checking.Checker;
import nl.tudelft.goalkeeper.checking.violations.Violation;
import nl.tudelft.goalkeeper.checking.violations.source.FileSource;
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

        ArrayList<Tuple2<File, Double>> results = new ArrayList<>();

        Rule rule = ruleSet.getRule(RULE_NAME);
        List<File> testFiles = FileParser.getTestFiles(files);
        for (File file: testFiles) {
            try {
                TestProgram program = setup(file.getAbsolutePath());
                TestRun run = new TestRun(program, false);
                // The environment must not start to run the test program.
                run.run(true);
                // Add the
                results.add(new Tuple2<File, Double>(file, 50.0));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (GOALRunFailedException e) {
                e.printStackTrace();
            }

            // Calculate the severity of the errors and parse the violations.
            results.forEach((tuple) -> {
                int severity  = rule.severityOf(tuple.getSecond());
                if (severity > 0) {

//                    rule.getStages().forEach((stage -> {
//
//                    }));
                    boolean error = severity >= ruleSet.getErrorSeverity();
                    violations.add(new Violation(VIOLATION_NAME, severity)
                    .setActualValue(tuple.getSecond())
                    //.setMinimumValue(rule.getStages().get())
                    .setSource(new FileSource(tuple.getFirst().getAbsolutePath()))
                    .setError(error));
                }
            });

        }
        return violations;
    }

    private TestProgram setup(String path) throws IOException {
        TestValidator testValidator = new TestValidator(path, new FileRegistry());
        testValidator.validate();
        TestProgram program = testValidator.getProgram();
        if (program != null && program.isValid()) {
            return program;
        } else {
            System.out.println(testValidator.getSyntaxErrors());
            System.out.println(testValidator.getErrors());
            throw new IOException();
        }
    }

}
