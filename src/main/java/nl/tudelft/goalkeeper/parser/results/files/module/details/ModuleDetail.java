package nl.tudelft.goalkeeper.parser.results.files.module.details;

import lombok.Getter;
import lombok.Setter;
import nl.tudelft.goalkeeper.checking.violations.source.Source;
import nl.tudelft.goalkeeper.parser.results.parts.Sourceable;

/**
 * Abstract class for EvaluationOrder and ExitCondition classes.
 */
public abstract class ModuleDetail implements Sourceable {
    @Getter @Setter private Source source = null;

    /**
     * Checks if the detail has a source.
     * @return True if it has a source, false otherwise.
     */
    public boolean hasSource() {
        return source != null;
    }
}
