package nl.tudelft.goalkeeper.parser;

import languageTools.analyzer.FileRegistry;
import languageTools.analyzer.mas.Analysis;
import languageTools.analyzer.mas.MASValidator;
import languageTools.errors.mas.MASError;
import languageTools.errors.mas.MASWarning;
import languageTools.program.actionspec.ActionSpecProgram;
import languageTools.program.agent.Module;
import lombok.Getter;
import lombok.Setter;
import nl.tudelft.goalkeeper.parser.results.ParseResult;
import nl.tudelft.goalkeeper.parser.results.files.actionspec.parsers.ActionSpecParser;
import nl.tudelft.goalkeeper.parser.results.files.mas.parsers.MasParser;
import nl.tudelft.goalkeeper.parser.results.files.module.parsers.MessageParser;
import nl.tudelft.goalkeeper.parser.results.files.module.parsers.ModuleParser;

import java.io.IOException;

/**
 * Parses a MAS and returns the parsed data.
 */
public final class Parser {

    @Getter @Setter private MessageParser messageParser;
    @Getter @Setter private ModuleParser moduleParser;
    @Getter @Setter private MasParser masParser;
    @Getter @Setter private ActionSpecParser actionSpecParser;
    @Getter @Setter private MASValidator validator;
    @Getter @Setter private FileLinker fileLinker;

    /**
     * Creates a new Parser instance.
     * @param fileName File path of a .mas2g file.
     */
    public Parser(String fileName) {
        actionSpecParser = new ActionSpecParser();
        messageParser = new MessageParser();
        moduleParser = new ModuleParser();
        masParser = new MasParser();
        validator = new MASValidator(fileName, new FileRegistry());
        fileLinker = new FileLinker();
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

        validator.getRegistry().getAllErrors().forEach(err -> {
            if (err.getType() != MASError.ENVIRONMENT_COULDNOT_FIND) {
                result.addViolation((messageParser).parse(err).setError(true));
                result.setSuccessful(false);
            }
        });

        validator.getRegistry().getWarnings().forEach(err -> {
            if (err.getType() != MASWarning.LAUNCH_CONDITIONAL_RULE) {
                result.addViolation(((messageParser).parse(err).setError(false)));
            }
        });

        if (result.isSuccessful()) {
            convert(result, analysis);
            fileLinker.link(result);
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
        try {
            result.setMasFile(masParser.parse(analysis.getProgram()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
