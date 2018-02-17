package nl.tudelft.goalkeeper.parser.results.files.mas2g.Launch;


import languageTools.program.mas.Entity;

import java.util.List;

/**
 * Created by Cedric Willekens (4530373) on 2/17/2018.
 */
public class LaunchRule {

    private Entity entity;

    private List<LaunchInstruction> launchInstructions;

    public LaunchRule(Entity entity, List<LaunchInstruction> launchInstructions) {
        this.entity = entity;
        this.launchInstructions = launchInstructions;
    }

}
