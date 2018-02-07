package nl.tudelft.goalkeeper.parser.results.files.module.actions;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import nl.tudelft.goalkeeper.checking.violations.source.Source;
import nl.tudelft.goalkeeper.parser.results.parts.Sourceable;

/**
 * Class for module exit actions.
 */
@EqualsAndHashCode
public final class ExitAction implements Action, Sourceable {
    private static final String IDENTIFIER = "exit-module/0";
    @Getter @Setter private Source source;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getIdentifier() {
        return IDENTIFIER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getIdentifier();
    }
}
