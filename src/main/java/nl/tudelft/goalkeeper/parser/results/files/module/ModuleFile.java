package nl.tudelft.goalkeeper.parser.results.files.module;

import lombok.Getter;
import lombok.Setter;
import nl.tudelft.goalkeeper.parser.results.files.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class which holds data on a module file.
 */
public class ModuleFile extends File implements Module {

    private List<Rule> rules;
    @Getter @Setter private String name; //NOPMD PMD can't handle Lombok.

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
     * {@inheritDoc}
     */
    @Override
    public List<Rule> getRules() {
        return Collections.unmodifiableList(rules);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addRule(Rule rule) {
        rules.add(rule);
    }

}
