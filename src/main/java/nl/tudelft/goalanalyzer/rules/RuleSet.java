package nl.tudelft.goalanalyzer.rules;

import lombok.Getter;
import nl.tudelft.goalanalyzer.exceptions.MalformedRulesException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


/**
 * Class which holds all rules that should be applied.
 */
public final class RuleSet {

    private Map<String, Rule> rules;
    @Getter private int errorSeverity;

    /**
     * Constructor for rule sets.
     */
    private RuleSet(HashMap<String, Rule> rules, int errorSeverity) {
        this.rules = rules;
        this.errorSeverity = errorSeverity;
    }

    /**
     * Load the rule set.
     * @param path Path of the rule set file.
     * @throws MalformedRulesException Thrown when rules file is invalid.
     */
    public static RuleSet load(String path) throws MalformedRulesException, IOException {
        RuleSetParser parser = new RuleSetParser();
        new String(Files.readAllBytes(Paths.get(path)));
        parser.parse(path);
        int errorSeverity = parser.getErrorSeverity();

        return new RuleSet(null, errorSeverity);
    }
}
