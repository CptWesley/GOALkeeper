package nl.tudelft.goalkeeper.parser;

import languageTools.analyzer.FileRegistry;
import languageTools.analyzer.mas.MASValidator;
import languageTools.errors.Message;

/**
 * Parses a MAS and returns the parsed data.
 */
public final class Parser {

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
            result.addViolation(MessageParser.parse(error));
            result.setSuccessful(false);
        }
        for (Message error : validator.getSyntaxErrors()) {
            result.addViolation(MessageParser.parse(error));
            result.setSuccessful(false);
        }
        for (Message error : validator.getWarnings()) {
            result.addViolation(MessageParser.parse(error).setError(false));
        }

        return result;
    }
}
