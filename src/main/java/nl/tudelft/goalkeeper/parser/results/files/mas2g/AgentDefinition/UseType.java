package nl.tudelft.goalkeeper.parser.results.files.mas2g.AgentDefinition;

/**
 * Created by Cedric Willekens (4530373) on 2/17/2018.
 */
public enum UseType {

    MAIN("main-module"),
    INIT("init-module"),
    EVENT("event-module");


    private String type;

    UseType(String s) {
        this.type = s;
    }
}
