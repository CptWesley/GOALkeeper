package nl.tudelft.goalkeeper.parser.results.files.mas;

import lombok.Getter;
import lombok.Setter;
import nl.tudelft.goalkeeper.checking.violations.source.Source;
import nl.tudelft.goalkeeper.parser.results.parts.Sourceable;

/**
 * Class representing an agent definition.
 */
public final class AgentDefinition implements Sourceable {

    @Getter @Setter Source source;
}
