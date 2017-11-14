package nl.tudelft.goalanalyzer.rules;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Getter;
import nl.tudelft.goalanalyzer.exceptions.MalformedRulesException;
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
     * @throws MalformedRulesException Thrown when content is not valid json.
     */
    void parse(String content) throws MalformedRulesException {
        try {
            object = new JsonParser().parse(content).getAsJsonObject();
        } catch (IllegalStateException e) {
            throw new MalformedRulesException("Invalid JSON file.");
        }
        parsed = true;
    }

    /**
     * Gets the minimal severity level that's considered to be an error.
     * @return Minimal error severity level.
     * @throws MalformedRulesException Thrown when format is incorrect.
     */
    int getErrorSeverity() throws MalformedRulesException {
        if (!isParsed() || object == null) {
            throw new NotParsedException();
        }
        JsonElement element = object.get("error-severity");
        if (element == null) {
            throw new MalformedRulesException("Missing 'error-severity' setting.");
        }
        try {
            return Integer.parseInt(element.toString());
        } catch (NumberFormatException e) {
            throw new MalformedRulesException("Invalid 'error-severity' type.");
        }
    }
}
