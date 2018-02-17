package nl.tudelft.goalkeeper.parser;

import languageTools.analyzer.FileRegistry;
import languageTools.analyzer.mas.Analysis;
import languageTools.analyzer.mas.MASValidator;
import languageTools.errors.Message;
import lombok.Getter;
import lombok.Setter;
import nl.tudelft.goalkeeper.parser.results.ParseResult;
import languageTools.program.agent.Module;
import nl.tudelft.goalkeeper.parser.results.files.module.parsers.MessageParser;
import nl.tudelft.goalkeeper.parser.results.files.module.parsers.ModuleParser;

import java.io.IOException;

/**
 * Parses a MAS and returns the parsed data.
 */
public final class Parser {

    @Getter @Setter private MessageParser messageParser;
    @Getter @Setter private ModuleParser moduleParser;
    @Getter @Setter private MASValidator validator;

    /**
     * Creates a new Parser instance.
     * @param fileName File path of a .mas2g file.
     */
    public Parser(String fileName) {
        messageParser = new MessageParser();
        moduleParser = new ModuleParser();
        validator = new MASValidator(fileName, new FileRegistry());
    }

    /**
     * Parses the mas from given file path.
     * @return Results of parsing.
     */
    public ParseResult parse() {
        ParseResult result = new ParseResult();
        result.setSuccessful(true);
        validator.validate();
        Analysis analysis = validator.process();

        for (Message error : validator.getErrors()) {
            result.addViolation(messageParser.parse(error).setError(true));
            result.setSuccessful(false);
        }
        for (Message error : validator.getSyntaxErrors()) {
            result.addViolation(messageParser.parse(error).setError(true));
            result.setSuccessful(false);
        }
        for (Message error : validator.getWarnings()) {
            result.addViolation(messageParser.parse(error).setError(false));
        }

        if (result.isSuccessful()) {
            for (Module m : analysis.getModuleDefinitions()) {
                try {
                    result.addModule(moduleParser.parseToFile(m));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
