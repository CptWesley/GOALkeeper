package nl.tudelft.goalanalyzer.rules;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Getter;
import nl.tudelft.goalanalyzer.exceptions.MalformedRulesException;
import nl.tudelft.goalanalyzer.exceptions.NotParsedException;
import nl.tudelft.goalanalyzer.util.VerifiedJsonParser;

import java.util.HashMap;
import java.util.Map;

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
        return VerifiedJsonParser.getInteger(object, "error-severity");
    }

    /**
     * Gets the rules from the rule set.
     * @return Rule to parse.
     * @throws MalformedRulesException Thrown when the rules are not properly formatted.
     */
    Map<String, Rule> getRules() throws MalformedRulesException {
        if (!isParsed() || object == null) {
            throw new NotParsedException();
        }
        JsonElement rulesElement = VerifiedJsonParser.getElement(object, "rules");
        Map<String, Rule> rules = new HashMap<>();
        try {
            for (JsonElement ruleElement : rulesElement.getAsJsonArray()) {
                JsonObject ruleObject = ruleElement.getAsJsonObject();
                String name = VerifiedJsonParser.getString(ruleObject, "name");
                Rule rule = parseRule(ruleObject);
                rules.put(name, rule);
            }
        } catch (Exception e) {
            throw new MalformedRulesException("Invalid 'rules' content format.");
        }
        return rules;
    }

    /**
     * Parses a json object to a rule.
     * @param object Json Object to parse.
     * @return Rule created from json.
     * @throws MalformedRulesException Thrown when rule json is malformed.
     */
    private Rule parseRule(JsonObject object) throws MalformedRulesException {
        Rule rule = new Rule();
        JsonElement stagesElement = VerifiedJsonParser.getElement(object, "stages");
        try {
            for (JsonElement stageElement : stagesElement.getAsJsonArray()) {
                JsonObject stageObject = stageElement.getAsJsonObject();
                rule.addStage(parseStage(stageObject));
            }
        } catch (Exception e) {
            throw new MalformedRulesException("Invalid 'stages' content format.");
        }
        return rule;
    }

    /**
     * Parses a json object to a stage.
     * @param object Json Object to parse.
     * @return Stage created from json.
     * @throws MalformedRulesException Thrown when stage json is malformed.
     */
    private Stage parseStage(JsonObject object) {
        Stage stage = new Stage();
        try {
            stage.setSeverity(VerifiedJsonParser.getInteger(object, "severity"));
        } catch (MalformedRulesException ignored) { /* Keep default values. */ }
        try {
            stage.setMin(VerifiedJsonParser.getInteger(object, "min"));
        } catch (MalformedRulesException ignored) { /* Keep default values. */ }
        try {
            stage.setMax(VerifiedJsonParser.getInteger(object, "max"));
        } catch (MalformedRulesException ignored) { /* Keep default values. */ }
        return stage;
    }
}
