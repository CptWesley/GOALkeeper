package nl.tudelft.goalkeeper.checking.checkers;

import languageTools.analyzer.FileRegistry;
import languageTools.analyzer.Validator;
import languageTools.analyzer.actionspec.ActionSpecValidator;
import languageTools.errors.Message;
import nl.tudelft.goalkeeper.checking.Checker;
import nl.tudelft.goalkeeper.checking.violations.Violation;
import nl.tudelft.goalkeeper.checking.violations.source.LineSource;
import nl.tudelft.goalkeeper.rules.RuleSet;
import nl.tudelft.goalkeeper.util.FileParser;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * A checker for act2g files to check the grammar.
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
     * This converts the validators which contain the syntax errors from the act2g files.
     * @param validators The list of validators created for the files.
     * @param ruleSet The rulesset.
     * @return The errors which are stored in the validators but as Violation instance.
     */
    private ArrayList<Violation> parseViolation(List<ActionSpecValidator> validators,
                                                RuleSet ruleSet) {
        ArrayList<Violation> violations = new ArrayList<>();
        for (Validator validator: validators) {
            Set<Message> errors = validator.getErrors();
            errors.addAll(validator.getSyntaxErrors());
            for (Message err: errors) {
                violations.add(new Violation("Syntax Error",
                        ruleSet.getErrorSeverity()).setError(true)
                        .setSource(new LineSource(
                                err.getSource().getSource(),
                                err.getSource().getLineNumber()))
                        .setSuggestion(err.toShortString()));
            }
        }
        return violations;
    }
}
