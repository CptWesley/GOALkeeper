package nl.tudelft.goalanalyzer.rules;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.media.sound.InvalidFormatException;
import lombok.Getter;
import nl.tudelft.goalanalyzer.exceptions.NotParsedException;

/**
 * Class which parses json rule sets.
 */
class RuleSetParser {

    @Getter private boolean parsed;
    private JsonObject object;

    /**
     * Constructor for the rule set parser.
     * @param content JSON string representing our rule set.
     */
    void parse(String content) throws InvalidFormatException {
        try {
            object = new JsonParser().parse(content).getAsJsonObject();
        } catch (IllegalStateException e) {
            throw new InvalidFormatException("Invalid JSON file.");
        }
        parsed = true;
    }

    /**
     * Gets the minimal severity level that's considered to be an error.
     * @return Minimal error severity level.
     * @throws InvalidFormatException Thrown when format is incorrect.
     */
    int getErrorSeverity() throws InvalidFormatException {
        if (!isParsed() || object == null) {
            throw new NotParsedException();
        }
        JsonElement element = object.get("error-severity");
        if (element == null) {
            throw new InvalidFormatException("Missing 'error-severity' setting.");
        }
        try {
            return Integer.parseInt(element.toString());
        } catch (NumberFormatException e) {
            throw new InvalidFormatException("Invalid 'error-severity' type.");
        }
    }
}
