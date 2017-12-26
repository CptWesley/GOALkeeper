package nl.tudelft.goalkeeper.parser;

import languageTools.analyzer.FileRegistry;
import languageTools.analyzer.mas.MASValidator;
import languageTools.errors.Message;
import nl.tudelft.goalkeeper.checking.violations.Violation;

/**
 * Parses a MAS and returns the parsed data.
 */
public final class Parser {

    private static final int SYNTAX_SEVERITY = 1;

    /**
     * Parses the mas from given file path.
     * @param fileName File path of a .mas2g file.
     * @return Results of parsing.
     */
    public ParseResult parse(String fileName) {
        ParseResult result = new ParseResult();
        result.setSuccessful(true);
        MASValidator validator = new MASValidator(fileName, new FileRegistry());
        validator.validate();
        validator.process();

        for (Message error : validator.getErrors()) {
            result.addViolation(toViolation(error));
            result.setSuccessful(false);
        }
        for (Message error : validator.getSyntaxErrors()) {
            result.addViolation(toViolation(error));
            result.setSuccessful(false);
        }
        for (Message error : validator.getWarnings()) {
            result.addViolation(toViolation(error).setError(false));
        }

        return result;
    }

    protected static Violation toViolation(Message error) {
        return new Violation(error.toShortString(), SYNTAX_SEVERITY)
                .setFile(error.getSource().getSource())
                .setStartingLine(error.getSource().getLineNumber())
                .setEndingLine(error.getSource().getLineNumber())
                .setError(true);
    }
}
