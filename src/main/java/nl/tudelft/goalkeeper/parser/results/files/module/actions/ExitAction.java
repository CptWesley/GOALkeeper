package nl.tudelft.goalkeeper.parser.results.files.module.actions;

import lombok.EqualsAndHashCode;

/**
 * Class for module exit actions.
 */
@EqualsAndHashCode
public final class ExitAction implements Action {
    private static final String IDENTIFIER = "exit-module/0";

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
