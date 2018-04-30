package nl.tudelft.goalkeeper.parser.results.files.mas.parsers;

import lombok.Getter;
import lombok.Setter;
import nl.tudelft.goalkeeper.checking.violations.source.SourceParser;
import nl.tudelft.goalkeeper.parser.results.files.mas.AgentDefinition;

/**
 * Class used for parsing agent definitions.
 */
public final class AgentDefinitionParser {

    @Getter @Setter private SourceParser sourceParser;

    /**
     * Creates a new AgentDefinitionParser instance.
     */
    public AgentDefinitionParser() {
        sourceParser = new SourceParser();
    }

    /**
     * Parses a GOAL agent definition to a GOALKeeper agent definition.
     * @param a GOAL agent definition.
     * @return A GOALKeeper version of an agent definition.
     */
    public AgentDefinition parse(languageTools.program.mas.AgentDefinition a) {
        AgentDefinition definition = new AgentDefinition(a.getName());
        definition.setSource(sourceParser.parse(a.getSourceInfo()));

        return definition;
    }
}
