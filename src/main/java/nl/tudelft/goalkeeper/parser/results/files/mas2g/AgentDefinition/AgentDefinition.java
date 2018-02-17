package nl.tudelft.goalkeeper.parser.results.files.mas2g.AgentDefinition;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

/**
 * Created by Cedric Willekens (4530373) on 2/17/2018.
 */
public class AgentDefinition {
    @Setter private List<UseClause> useClauses;

    @Getter @Setter private String relativePath;

    public AgentDefinition(List<UseClause> useClauses) {
        this.useClauses = useClauses;
    }

    public List<UseClause> getUseClauses() {
        return Collections.unmodifiableList(useClauses);
    }

    public void addUseClause(UseClause useClause) {
        this.useClauses.add(useClause);
    }
}
