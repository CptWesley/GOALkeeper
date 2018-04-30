package nl.tudelft.goalkeeper.parser.results;

import lombok.Getter;
import lombok.Setter;
import nl.tudelft.goalkeeper.checking.violations.Violation;
import nl.tudelft.goalkeeper.parser.results.files.actionspec.ActionSpecFile;
import nl.tudelft.goalkeeper.parser.results.files.mas.Mas;
import nl.tudelft.goalkeeper.parser.results.files.module.ModuleFile;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Contains the results of parsing.
 */
public final class ParseResult {

    private List<Violation> violations;
    @Getter @Setter private boolean successful; //NOPMD PMD can't handle Lombok.

    private List<ModuleFile> modules;
    private List<ActionSpecFile> actionSpecs;
    @Getter @Setter private Mas mas; //NOPMD PMD can't handle Lombok.

    /**
     * Creates a new parse result class.
     */
    public ParseResult() {
        violations = new LinkedList<>();
        successful = false;
        modules = new LinkedList<>();
        actionSpecs = new LinkedList<>();
    }

    /**
     * Adds a violation.
     * @param violation Violation to add.
     * @return Current object.
     */
    public ParseResult addViolation(Violation violation) {
        violations.add(violation);
        return this;
    }

    /**
     * Gets all violations from the parser.
     * @return List of all violations found by the parser.
     */
    public List<Violation> getViolations() {
        return Collections.unmodifiableList(violations);
    }

    /**
     * Adds a module.
     * @param module Module to add.
     * @return Current object.
     */
    public ParseResult addModule(ModuleFile module) {
        modules.add(module);
        return this;
    }

    /**
     * Gets all modules.
     * @return List of all modules.
     */
    public List<ModuleFile> getModules() {
        return Collections.unmodifiableList(modules);
    }

    /**
     * Adds an actionspec.
     * @param actionSpec Actionspec to add.
     * @return Current object.
     */
    public ParseResult addActionSpec(ActionSpecFile actionSpec) {
        actionSpecs.add(actionSpec);
        return this;
    }

    /**
     * Gets all actionspecs.
     * @return List of all actionspecs.
     */
    public List<ActionSpecFile> getActionSpecs() {
        return Collections.unmodifiableList(actionSpecs);
    }
}
