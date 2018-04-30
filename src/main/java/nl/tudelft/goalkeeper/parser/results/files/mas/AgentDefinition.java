package nl.tudelft.goalkeeper.parser.results.files.mas;

import lombok.Getter;
import lombok.Setter;
import nl.tudelft.goalkeeper.checking.violations.source.Source;
import nl.tudelft.goalkeeper.parser.results.parts.Sourceable;

/**
 * Class representing an agent definition.
 */
public final class AgentDefinition implements Sourceable {

    @Getter @Setter private Source source;
    @Getter private String name;

    /**
     * Creates a new AgentDefinitionInstance.
     * @param name Name of the agent.
     */
    public AgentDefinition(String name) {
        this.name = name;
    }
}
