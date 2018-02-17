package nl.tudelft.goalkeeper.parser.results.files.mas2g.AgentDefinition;

import lombok.Getter;
import lombok.Setter;
import nl.tudelft.goalkeeper.parser.results.files.File;

/**
 * Created by Cedric Willekens (4530373) on 2/17/2018.
 */
public class UseClause {

    @Getter private String reference;

    @Getter private UseType type;

    @Getter @Setter private File file;

    public UseClause(String reference, UseType type) {
        this.reference = reference;
        this.type = type;
    }
}
