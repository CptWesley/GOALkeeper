package nl.tudelft.goalkeeper.parser.results.files.mas.parsers;

import languageTools.program.agent.Module;
import lombok.Getter;
import lombok.Setter;
import nl.tudelft.goalkeeper.checking.violations.source.SourceParser;
import nl.tudelft.goalkeeper.parser.results.files.mas.AgentDefinition;
import nl.tudelft.goalkeeper.parser.results.files.mas.ModuleUsageDefinition;

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

        setUsageDefinitions(definition, a);

        return definition;
    }

    private void setUsageDefinitions(AgentDefinition definition,
                                     languageTools.program.mas.AgentDefinition a) {
        Module init = a.getInitModule();
        Module main = a.getMainModule();
        Module event = a.getEventModule();
        Module shutdown = a.getShutdownModule();
        if (init != null) {
            ModuleUsageDefinition usage
                    = new ModuleUsageDefinition(init.getSourceInfo().toString());
            usage.setSource(sourceParser.parse(a.getInitUseClause().getSourceInfo()));
            definition.setInitModule(usage);
        }
        if (main != null) {
            ModuleUsageDefinition usage
                    = new ModuleUsageDefinition(main.getSourceInfo().toString());
            usage.setSource(sourceParser.parse(a.getMainUseClause().getSourceInfo()));
            definition.setMainModule(usage);
        }
        if (event != null) {
            ModuleUsageDefinition usage
                    = new ModuleUsageDefinition(event.getSourceInfo().toString());
            usage.setSource(sourceParser.parse(a.getEventUseClause().getSourceInfo()));
            definition.setEventModule(usage);
        }
        if (shutdown != null) {
            ModuleUsageDefinition usage
                    = new ModuleUsageDefinition(shutdown.getSourceInfo().toString());
            usage.setSource(sourceParser.parse(a.getShutdownUseClause().getSourceInfo()));
            definition.setShutDownModule(usage);
        }
    }
}
