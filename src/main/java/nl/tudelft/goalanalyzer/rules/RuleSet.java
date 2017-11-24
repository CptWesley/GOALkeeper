package nl.tudelft.goalanalyzer.rules;

import lombok.Getter;
import nl.tudelft.goalanalyzer.exceptions.MalformedRulesException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;


/**
 * Class which holds all rules that should be applied.
 */
public final class RuleSet {

    private Map<String, Rule> rules;
    @Getter private int errorSeverity; //NOPMD
    private boolean failOnError;

    /**
     * Constructor for rule sets.
     */
    private RuleSet(Map<String, Rule> rules,
                    int errorSeverity, boolean failOnError) {
        this.rules = rules;
        this.errorSeverity = errorSeverity;
        this.failOnError = failOnError;
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
        String content = new String(Files.readAllBytes(Paths.get(path)), "UTF-8");
        parser.parse(content);
        int errorSeverity = parser.getErrorSeverity();
        boolean failOnError = parser.getFailOnError();
        Map<String, Rule> rules = parser.getRules();

        return new RuleSet(rules, errorSeverity, failOnError);
    }

    /**
     * Gets a rule with a certain name.
     * @param name Name of the rule.
     * @return Rule we searched for.
     */
    public Rule getRule(String name) {
        return rules.get(name);
    }

    /**
     * Checks if we have a rule with a certain name.
     * @param name Name of the rule.
     * @return True if we have it, false otherwise.
     */
    public boolean hasRule(String name) {
        return getRule(name) != null;
    }
}
