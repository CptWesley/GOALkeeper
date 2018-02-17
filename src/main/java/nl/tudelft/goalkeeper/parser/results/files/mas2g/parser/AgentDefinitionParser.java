package nl.tudelft.goalkeeper.parser.results.files.mas2g.parser;



import nl.tudelft.goalkeeper.exceptions.NotParsedException;
import nl.tudelft.goalkeeper.parser.results.files.mas2g.AgentDefinition.AgentDefinition;
import nl.tudelft.goalkeeper.parser.results.files.mas2g.AgentDefinition.UseClause;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cedric Willekens (4530373) on 2/17/2018.
 */
public class AgentDefinitionParser {
    private AgentDefinitionParser() {}

    public static AgentDefinition parse(languageTools.program.mas.AgentDefinition agent) {
        List<UseClause> useClauses
                = new ArrayList<>();
        for (languageTools.program.mas.UseClause clause: agent.getUseClauses()) {
            try {
                useClauses.add(UseClauseParser.parse(clause));
            } catch (IOException e) {
                throw new NotParsedException("We were unable to parse: " + clause.getReference());
            }
        }
        return new AgentDefinition(useClauses);
    }
}
