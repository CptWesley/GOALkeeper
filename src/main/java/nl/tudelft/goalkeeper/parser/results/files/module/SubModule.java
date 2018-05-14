package nl.tudelft.goalkeeper.parser.results.files.module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class representing a submodule.
 */
public final class SubModule implements Module {

    private List<ModuleRule> rules;

    /**
     * Creates a new submodule instance.
     */
    public SubModule() {
        rules = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ModuleRule> getRules() {
        return Collections.unmodifiableList(rules);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addRule(ModuleRule rule) {
        rules.add(rule);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ModuleRule rule : rules) {
            sb.append(System.getProperty("line.separator")).append(rule);
        }
        return sb.toString();
    }
}
