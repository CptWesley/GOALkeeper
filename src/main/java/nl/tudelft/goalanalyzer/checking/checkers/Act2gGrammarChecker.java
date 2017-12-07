package nl.tudelft.goalanalyzer.checking.checkers;

import languageTools.analyzer.FileRegistry;
import languageTools.analyzer.Validator;
import languageTools.analyzer.actionspec.ActionSpecValidator;
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
public class Act2gGrammarChecker implements CheckerInterface {

    /**
     * This checks the files which end in .act2g to make sure they compile correctly.
     * @param files Files to check.
     * @param ruleSet RuleSet to run with.
     * @return The errors created by the goal grammar parser.
     */
    @Override
    public Collection<Violation> run(String[] files, RuleSet ruleSet) {
        List<File> fileList = FileParser.getActionFiles(files);
        List<ActionSpecValidator> validators = new ArrayList<>();
        for (File file: fileList) {
            ActionSpecValidator validator = new ActionSpecValidator(file.getAbsolutePath(),
                    new FileRegistry());
            validator.validate();
            validators.add(validator);
        }
        return parseViolation(validators, ruleSet);
    }

    /**
     * This converts the validators which contain the syntax errors from the act2g files
     * @param validators The list of validators created for the files.
     * @param ruleSet The rulesset.
     * @return The errors which are stored in the validators but as Violation instance.
     */
    private ArrayList<Violation> parseViolation(List<ActionSpecValidator> validators, RuleSet ruleSet) {
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
