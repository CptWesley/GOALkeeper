package nl.tudelft.goalkeeper.parser.results.files.module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class representing a submodule.
 */
public final class SubModule implements Module {

    private List<Rule> rules;

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
