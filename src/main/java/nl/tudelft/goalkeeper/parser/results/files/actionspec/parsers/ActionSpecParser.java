package nl.tudelft.goalkeeper.parser.results.files.actionspec.parsers;

import languageTools.program.actionspec.ActionSpecProgram;
import languageTools.program.actionspec.UserSpecAction;
import nl.tudelft.goalkeeper.parser.results.files.actionspec.ActionSpecFile;

import java.io.IOException;

/**
 * Class which parses .act2g files.
 */
public final class ActionSpecParser {

    /**
     * Prevents instantiation.
     */
    private ActionSpecParser() { }

    /**
     * Converts a GOAL ActionSpecProgram into a GOALkeeper ActionSpecFile.
     * @param asp ActionSpecProgram to convert.
     * @return An ActionSpecFile version of the ActionSpecProgram.
     * @throws IOException Thrown when there is a problem reading the file.
     */
    public static ActionSpecFile parse(ActionSpecProgram asp) throws IOException {
        ActionSpecFile result = new ActionSpecFile(asp.getSourceFile().toString());
        for (UserSpecAction action : asp.getActionSpecifications()) {
            result.addActionSpecification(ActionSpecificationParser.parse(action));
        }
        return result;
    }
}
