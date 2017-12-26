package nl.tudelft.goalkeeper.parser;

import nl.tudelft.goalkeeper.checking.violations.Violation;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Contains the results of parsing.
 */
public final class ParseResult {

    private List<Violation> violations;

    /**
     * Creates a new parse result class.
     */
    ParseResult() {
        violations = new LinkedList<>();
    }

    /**
     * Adds a violation.
     * @param violation Violation to add.
     */
    void addViolation(Violation violation) {
        violations.add(violation);
    }

    /**
     * Gets all violations from the parser.
     * @return List of all violations found by the parser.
     */
    public List<Violation> getViolations() {
        return Collections.unmodifiableList(violations);
    }
}
