package nl.tudelft.goalkeeper.parser.results.files.actionspec.parsers;

import languageTools.program.actionspec.ActionSpecProgram;
import languageTools.program.actionspec.UserSpecAction;
import nl.tudelft.goalkeeper.parser.results.files.actionspec.ActionSpecFile;
import nl.tudelft.goalkeeper.parser.results.files.actionspec.ActionSpecification;
import nl.tudelft.goalkeeper.parser.results.parts.KRLanguage;

import java.io.IOException;

/**
 * Class which parses .act2g files.
 */
public final class ActionSpecParser {

    /**
     * Makes initiation possible
     */
    public ActionSpecParser() { }

    /**
     * Converts a GOAL ActionSpecProgram into a GOALkeeper ActionSpecFile.
     * @param asp ActionSpecProgram to convert.
     * @return An ActionSpecFile version of the ActionSpecProgram.
     * @throws IOException Thrown when there is a problem reading the file.
     */
    public static ActionSpecFile parse(ActionSpecProgram asp) throws IOException {
        ActionSpecFile result = new ActionSpecFile(asp.getSourceFile().toString());
        KRLanguage language = KRLanguage.UNKNOWN;
        for (UserSpecAction action : asp.getActionSpecifications()) {
            ActionSpecification actionSpec = ActionSpecificationParser.parse(action);
            result.addActionSpecification(actionSpec);
            if (actionSpec.getKRLanguage() != KRLanguage.UNKNOWN) {
                language = actionSpec.getKRLanguage();
            }
        }
        result.setKRLanguage(language);
        return result;
    }
}
