package nl.tudelft.goalkeeper.parser;

import languageTools.analyzer.FileRegistry;
import languageTools.analyzer.mas.Analysis;
import languageTools.analyzer.mas.MASValidator;
import languageTools.errors.Message;
import languageTools.program.actionspec.ActionSpecProgram;
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
        Analysis analysis = validator.process();

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
    private static void convert(ParseResult result, Analysis analysis) {
        for (Module m : analysis.getModuleDefinitions()) {
            try {
                result.addModule(ModuleParser.parseToFile(m));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (ActionSpecProgram actionSpec : analysis.getActionSpecDefinitions()) {
            try {
                result.addActionSpec(ActionSpecParser.parse(actionSpec));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
