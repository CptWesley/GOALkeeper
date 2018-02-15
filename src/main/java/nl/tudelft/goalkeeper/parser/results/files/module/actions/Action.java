package nl.tudelft.goalkeeper.parser.results.files.module.actions;

import lombok.Getter;
import lombok.Setter;
import nl.tudelft.goalkeeper.checking.violations.source.Source;
import nl.tudelft.goalkeeper.parser.results.parts.Sourceable;

/**
 * Abstract class for actions.
 */
public abstract class Action implements Sourceable {

    /**
     * Get the identifier of this action.
     * @return Identifier of the action.
     */
    public abstract String getIdentifier();

    @Getter @Setter private Source source;
}
