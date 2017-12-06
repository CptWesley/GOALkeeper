package nl.tudelft.goalanalyzer.checking.checkers;

import languageTools.analyzer.FileRegistry;
import languageTools.analyzer.Validator;
import languageTools.analyzer.mas.MASValidator;
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

@Checker
public class GrammarChecker implements CheckerInterface {
    @Override
    public Collection<Violation> run(String[] files, RuleSet ruleSet) {
        List<File> fileList = FileParser.getMasFiles(files);
        List<Validator> validators = new ArrayList<>();
        for (File file: fileList) {
            MASValidator validator = new MASValidator(file.getAbsolutePath(), new FileRegistry());
            validator.validate();
            validators.add(validator);
        }
        return parseViolation(validators);
    }

    private ArrayList<Violation> parseViolation(List<Validator> validators) {
        ArrayList<Violation> violations = new ArrayList<>();
        for (Validator validator: validators) {
            Set<Message> errors = validator.getErrors();
            errors.addAll(validator.getSyntaxErrors());
            for (Message err: errors) {
                violations.add(new Violation(err.toString(), Integer.MAX_VALUE));
            }
        }
        return violations;
    }
}


