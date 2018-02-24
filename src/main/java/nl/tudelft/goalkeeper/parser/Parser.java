package nl.tudelft.goalkeeper.parser;

import languageTools.analyzer.FileRegistry;
import languageTools.analyzer.mas.Analysis;
import languageTools.analyzer.mas.MASValidator;
import languageTools.errors.Message;
import languageTools.program.actionspec.ActionSpecProgram;
import lombok.Getter;
import lombok.Setter;
import nl.tudelft.goalkeeper.parser.results.ParseResult;
import languageTools.program.agent.Module;
import nl.tudelft.goalkeeper.parser.results.files.actionspec.parsers.ActionSpecParser;
import nl.tudelft.goalkeeper.parser.results.files.module.parsers.MessageParser;
import nl.tudelft.goalkeeper.parser.results.files.module.parsers.ModuleParser;

import java.io.IOException;

/**
 * Parses a MAS and returns the parsed data.
 */
public final class Parser {

    @Getter @Setter private MessageParser messageParser;
    @Getter @Setter private ModuleParser moduleParser;
    @Getter @Setter private ActionSpecParser actionSpecParser;
    @Getter @Setter private MASValidator validator;

    /**
     * Creates a new Parser instance.
     * @param fileName File path of a .mas2g file.
     */
    public Parser(String fileName) {
        actionSpecParser = new ActionSpecParser();
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

        for (Message error : validator.getRegistry().getErrors()) {
            result.addViolation(messageParser.parse(error).setError(true));
            result.setSuccessful(false);
        }
        for (Message error : validator.getRegistry().getSyntaxErrors()) {
            result.addViolation(messageParser.parse(error).setError(true));
            result.setSuccessful(false);
        }
        for (Message error : validator.getRegistry().getWarnings()) {
            result.addViolation(messageParser.parse(error).setError(false));
        }

        if (result.isSuccessful()) {
            convert(result, analysis);

        }
        return result;
    }

    /**
     * Converts the results of the analysis to GOALkeeper formats.
     * @param result ParseResult to add the conversions to.
     * @param analysis Analysis to convert.
     */
    private void convert(ParseResult result, Analysis analysis) {
        for (Module m : analysis.getModuleDefinitions()) {
            try {
                ModuleParser parser = new ModuleParser();
                result.addModule(moduleParser.parseToFile(m));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (ActionSpecProgram actionSpec : analysis.getActionSpecDefinitions()) {
            try {

                result.addActionSpec(actionSpecParser.parse(actionSpec));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
