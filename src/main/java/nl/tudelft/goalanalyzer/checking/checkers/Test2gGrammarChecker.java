package nl.tudelft.goalanalyzer.checking.checkers;

import languageTools.analyzer.FileRegistry;
import languageTools.analyzer.Validator;
import languageTools.analyzer.mas.MASValidator;
import languageTools.analyzer.test.TestValidator;
import languageTools.errors.Message;
import nl.tudelft.goalanalyzer.checking.Checker;
import nl.tudelft.goalanalyzer.checking.violations.Violation;
import nl.tudelft.goalanalyzer.rules.RuleSet;
import nl.tudelft.goalanalyzer.util.FileParser;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by Cedric Willekens (4530373) on 12/6/2017.
 */
@Checker
public class Test2gGrammarChecker implements CheckerInterface {
    @Override
    public Collection<Violation> run(String[] files, RuleSet ruleSet) {
        List<File> fileList = FileParser.getTestFiles(files);
        List<TestValidator> validators = new ArrayList<>();
        for (File file: fileList) {
            TestValidator validator = new TestValidator(file.getAbsolutePath(), new FileRegistry());
            validator.validate();
            validators.add(validator);
        }
        return parseViolation(validators, ruleSet);
    }

    private ArrayList<Violation> parseViolation(List<TestValidator> validators, RuleSet ruleSet) {
        ArrayList<Violation> violations = new ArrayList<>();
        for (Validator validator: validators) {
            Set<Message> errors = validator.getErrors();
            errors.addAll(validator.getSyntaxErrors());
            for (Message err: errors) {
                violations.add(new Violation("Syntax Error", ruleSet.getErrorSeverity()).setError(true)
                        .setFile(err.getSource().getSource())
                        .setStartingLine(err.getSource().getLineNumber())
                        .setEndingLine(err.getSource().getLineNumber())
                        .setSuggestion(err.toShortString()));
            }
        }
        return violations;
    }
}
