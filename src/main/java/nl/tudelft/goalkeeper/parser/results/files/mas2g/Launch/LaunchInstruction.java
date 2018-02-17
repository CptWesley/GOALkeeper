package nl.tudelft.goalkeeper.parser.results.files.mas2g.Launch;

import nl.tudelft.goalkeeper.parser.results.files.mas2g.AgentDefinition.AgentDefinition;

/**
 * Created by Cedric Willekens (4530373) on 2/17/2018.
 */
public class LaunchInstruction {

    private String agentName;

    private AgentDefinition agentDefinition;

    public LaunchInstruction(String agentName, AgentDefinition agentDefinition) {
        this.agentName = agentName;
        this.agentDefinition = agentDefinition;
    }



}
