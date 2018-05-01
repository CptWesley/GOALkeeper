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
    @Getter @Setter private ModuleUsageDefinition initModule,
            mainModule, eventModule, shutDownModule;

    /**
     * Creates a new AgentDefinitionInstance.
     * @param name Name of the agent.
     */
    public AgentDefinition(String name) {
        this.name = name;
        this.initModule = null;
        this.mainModule = null;
        this.eventModule = null;
        this.shutDownModule = null;
    }

    /**
     * Checks if this agent definition has an init module specified.
     * @return True if it has an init module, false otherwise.
     */
    public boolean hasInitModule() {
        return initModule != null;
    }

    /**
     * Checks if this agent definition has a main module specified.
     * @return True if it has a main module, false otherwise.
     */
    public boolean hasMainModule() {
        return mainModule != null;
    }

    /**
     * Checks if this agent definition has a shutdown module specified.
     * @return True if it has a shutdown module, false otherwise.
     */
    public boolean hasShutdownModule() {
        return shutDownModule != null;
    }

    /**
     * Checks if this agent definition has an event module specified.
     * @return True if it has an event module, false otherwise.
     */
    public boolean hasEventModule() {
        return eventModule != null;
    }
}
