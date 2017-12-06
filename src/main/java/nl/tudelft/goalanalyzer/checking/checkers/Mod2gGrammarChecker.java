package nl.tudelft.goalanalyzer.checking.checkers;

import languageTools.analyzer.FileRegistry;
import languageTools.analyzer.module.ModuleValidator;
import languageTools.errors.Message;
import nl.tudelft.goalanalyzer.checking.violations.Violation;
import nl.tudelft.goalanalyzer.rules.RuleSet;
import nl.tudelft.goalanalyzer.util.FileParser;

import javax.xml.validation.Validator;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by Cedric Willekens (4530373) on 12/6/2017.
 */
public class Mod2gGrammarChecker implements CheckerInterface{
    @Override
    public Collection<Violation> run(String[] files, RuleSet ruleSet) {
        List<File> fileList = FileParser.getModuleFiles(files);
        List<ModuleValidator> validators = new ArrayList<>();
        for (File file : fileList) {
            ModuleValidator validator = new ModuleValidator(file.getAbsolutePath(),
                    new FileRegistry());
            validator.validate();
            validators.add(validator);
        }
        return parseViolation(validators, ruleSet);
    }

    private ArrayList<Violation> parseViolation(List<ModuleValidator> validators, RuleSet ruleSet) {
        ArrayList<Violation> violations = new ArrayList<>();
        for (ModuleValidator validator: validators) {
            Set<Message> errors = validator.getErrors();
            errors.addAll(validator.getSyntaxErrors());
            for (Message err : errors) {
                violations.add(new Violation("Syntax Error", ruleSet.getErrorSeverity())
                        .setFile(err.getSource().getSource())
                        .setStartingLine(err.getSource().getLineNumber())
                        .setEndingLine(err.getSource().getLineNumber())
                        .setSuggestion(err.toShortString()));
            }
        }
        return violations;
    }

}
