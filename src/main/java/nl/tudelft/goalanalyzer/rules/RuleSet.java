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
     * @return New rule set loaded from file.
     * @throws MalformedRulesException Thrown when rules file is invalid.
     * @throws IOException Thrown when there is an error while loading the file.
     */
    public static RuleSet load(String path) throws MalformedRulesException, IOException {
        RuleSetParser parser = new RuleSetParser();
        String content = new String(Files.readAllBytes(Paths.get(path)));
        parser.parse(content);
        int errorSeverity = parser.getErrorSeverity();

        return new RuleSet(null, errorSeverity);
    }
}
