package nl.tudelft.goalkeeper.parser.results.files.mas2g.AgentDefinition;

/**
 * Created by Cedric Willekens (4530373) on 2/17/2018.
 */
public class UseClause {

    private String reference;

    private UseType type;

    public UseClause(String reference, UseType type) {
        this.reference = reference;
        this.type = type;
    }
}
