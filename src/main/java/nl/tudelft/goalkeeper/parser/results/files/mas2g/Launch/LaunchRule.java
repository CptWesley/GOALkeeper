package nl.tudelft.goalkeeper.parser.results.files.mas2g.Launch;


import languageTools.program.mas.Entity;

/**
 * Created by Cedric Willekens (4530373) on 2/17/2018.
 */
public class LaunchRule {

    private Entity entity;

    private LaunchInstruction launchInstruction;

    public LaunchRule(Entity entity, LaunchInstruction launchInstruction) {
        this.entity = entity;
        this.launchInstruction = launchInstruction;
    }

}
