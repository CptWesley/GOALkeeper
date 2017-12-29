package nl.tudelft.goalkeeper.parser.results.files.module;

import nl.tudelft.goalkeeper.parser.results.files.File;
import nl.tudelft.goalkeeper.parser.results.files.module.rules.Rule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class which holds data on a module file.
 */
public class ModuleFile extends File {

    private List<Rule> rules;

    /**
     * Creates a new ModuleFile instance.
     * @param fileName File path to parse.
     * @throws IOException Thrown when file could not be read.
     */
    public ModuleFile(String fileName) throws IOException {
        super(fileName);
        this.rules = new ArrayList<>();
    }

    /**
     * Get a list of all rules in the module.
     * @return List of all rules in the module.
     */
    public List<Rule> getRules() {
        return Collections.unmodifiableList(rules);
    }

    /**
     * Adds a rule to the module.
     * @param rule Rule to be added.
     * @return Current object.
     */
    public ModuleFile addRule(Rule rule) {
        rules.add(rule);
        return this;
    }

}
