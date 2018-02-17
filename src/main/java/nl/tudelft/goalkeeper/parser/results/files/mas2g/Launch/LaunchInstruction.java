package nl.tudelft.goalkeeper.parser.results.files.mas2g.Launch;

import nl.tudelft.goalkeeper.parser.results.files.mas2g.AgentDefinitions;

/**
 * Created by Cedric Willekens (4530373) on 2/17/2018.
 */
public class LaunchInstruction {

    private String agentName;

    private AgentDefinitions agentDefinitions;

    public LaunchInstruction(String agentName, AgentDefinitions agentDefinitions) {
        this.agentName = agentName;
        this.agentDefinitions = agentDefinitions;
    }



}
