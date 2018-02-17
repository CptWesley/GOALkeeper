package nl.tudelft.goalkeeper.parser.results.files.mas2g.parser;

import nl.tudelft.goalkeeper.parser.results.files.mas2g.AgentDefinition.AgentDefinition;
import nl.tudelft.goalkeeper.parser.results.files.mas2g.Launch.LaunchInstruction;

/**
 * Created by Cedric Willekens (4530373) on 2/17/2018.
 */
public class LaunchInstructionParser {

    private LaunchInstructionParser() {}

    public static LaunchInstruction parse(languageTools.program.mas.LaunchInstruction instruction) {
        AgentDefinition definition = AgentDefinitionParser.parse(instruction.getAgentDf());
        return new LaunchInstruction(instruction.getAgentName()
                , definition);
    }
}
