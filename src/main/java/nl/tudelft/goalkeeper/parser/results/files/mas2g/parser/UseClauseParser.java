package nl.tudelft.goalkeeper.parser.results.files.mas2g.parser;

import nl.tudelft.goalkeeper.parser.results.files.File;
import nl.tudelft.goalkeeper.parser.results.files.mas2g.AgentDefinition.UseClause;
import nl.tudelft.goalkeeper.parser.results.files.mas2g.AgentDefinition.UseType;
import nl.tudelft.goalkeeper.parser.results.files.module.ModuleFile;

import java.io.IOException;

/**
 * Created by Cedric Willekens (4530373) on 2/17/2018.
 */
public class UseClauseParser {
    private UseClauseParser() {}

    public UseClause parse(languageTools.program.mas.UseClause useClause) throws IOException {
        languageTools.program.mas.UseClause.UseCase useCase = useClause.getUseCase();
        File file = new ModuleFile(useClause.getResolvedReference().getName());

        UseType type;

        switch (useCase) {
            case INIT: type = UseType.INIT;
                            break;
            case EVENT: type = UseType.EVENT;
                            break;
            case MAIN: type = UseType.MAIN;
                            break;
            default: type = UseType.UNKNOWN;
        }


        UseClause clause = new UseClause(useClause.getReference(), type);
        clause.setFile(file);
        return clause;
    }
}
