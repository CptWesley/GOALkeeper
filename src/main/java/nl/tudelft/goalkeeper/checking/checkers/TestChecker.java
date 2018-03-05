package nl.tudelft.goalkeeper.checking.checkers;

import goal.preferences.CorePreferences;
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
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Checker
public class TestChecker implements CheckerInterface {

    private static final String RULE_NAME = "TEST";
    private static final String VIOLATION_NAME = "Test file runner";

    /**
     * Method to check if a rule is enabled.
     *
     * @param ruleSet The set of rules.
     * @return True if the check is enabled, else false.
     */
    private boolean isEnabled(RuleSet ruleSet) {
        if (!ruleSet.hasRule(RULE_NAME)) {
            return false;
        }
        return ruleSet.getRule(RULE_NAME).isEnabled();
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public Collection<Violation> run(String[] files, RuleSet ruleSet) {
        CorePreferences.setAbortOnTestFailure(false);
        List<Violation> violations = new ArrayList<>();

        if (!isEnabled(ruleSet)) {
            return violations;
        }

        ArrayList<Tuple2<String, Double>> results = new ArrayList<>();

        Rule rule = ruleSet.getRule(RULE_NAME);
        List<File> testFiles = getTestFiles(files);
        for (File testFile : testFiles) {
            System.out.println("Running file: " + testFile.getAbsolutePath());
            TestRun test = null;
            try {
                test = runTest(testFile.getAbsolutePath());
            } catch (Exception e) {
                System.out.println("We were unable to run the test file: "
                        + testFile.getAbsolutePath());
                e.printStackTrace();
            }
            assert test != null;
            test.cleanup();
            // Add the result of the test run to the results list.
            results.add(new Tuple2<>(testFile.getName(), 0.0));

            // Calculate the severity of the errors and parse the violations.
            results.forEach((tuple) -> {
                int severity = rule.severityOf(tuple.getSecond());
                if (severity > 0) {
                    boolean error = severity >= ruleSet.getErrorSeverity();
                    violations.add(new Violation(VIOLATION_NAME, severity)
                            .setActualValue(tuple.getSecond())
                            .setSource(new FileSource(tuple.getFirst()))
                            .setError(error));
                }
            });
        }

        return violations;
    }

    /**
     * Method to run the test2g file.
     *
     * @param testFileName The test2g file to be run.
     * @return Returns the TestRun it ran containing all the information about the tests. //TODO Make sure that all the information about the tests is actually in there...
     * @throws GOALRunFailedException This exception is thrown when the program is unable to read
     *                                the file and thus also run it.
     */
    private TestRun runTest(String testFileName) throws GOALRunFailedException {
        TestProgram testProgram;
        try {
            testProgram = setup(testFileName);
        } catch (IOException e) {
            throw new GOALRunFailedException("error while reading test file " + testFileName, e);
        }

        assert testProgram != null;

        TestRun testRun = new TestRun(testProgram, false);
        testRun.run(true);

        return testRun;
    }

    /**
     * Method to setup the TestProgram so that it can be run.
     *
     * @param path The path to the test2g file which is to be run
     * @return An instance of TestProgram containing the test2g file to be run.
     * @throws Exception If the test2g file is not able to be read or does not exist
     *                   an IOException will be thrown.
     */
    private TestProgram setup(String path) throws IOException {
        TestValidator visitor = new TestValidator(path, new FileRegistry());
        visitor.validate();
        TestProgram program = visitor.getProgram();
        if (program != null) {
            return program;
        } else {
            System.out.println(visitor.getRegistry().getSyntaxErrors());
            System.out.println(visitor.getRegistry().getErrors());
            throw new IOException();
        }
    }

    private List<File> getTestFiles(String[] files) {
        List<File> fileList = new ArrayList<>();
        for (String file : files) {
            File temp = new File(file);
            if (temp.isFile() && FilenameUtils.getExtension(file).toLowerCase().equals("test2g")) {
                fileList.add(temp);
            }
        }
        return fileList;
    }

}
