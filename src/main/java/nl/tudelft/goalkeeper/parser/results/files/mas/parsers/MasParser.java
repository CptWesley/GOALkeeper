package nl.tudelft.goalkeeper.parser.results.files.mas.parsers;

import languageTools.program.mas.MASProgram;
import lombok.Getter;
import lombok.Setter;
import nl.tudelft.goalkeeper.parser.results.files.mas.MasFile;

import java.io.IOException;

/**
 * Class which parses mas2g files.
 */
public final class MasParser {

    @Getter @Setter private AgentDefinitionParser agentDefinitionParser;

    /**
     * Creates a new instance of the MasParser class.
     */
    public MasParser() {
        agentDefinitionParser = new AgentDefinitionParser();
    }

    /**
     * Parses a GOAL MASProgram to a Mas.
     * @param program Program to parse.
     * @return GOALKeeper variant of the MASProgram.
     * @throws IOException Thrown when there is a problem reading the file.
     */
    public MasFile parse(MASProgram program) throws IOException {
        MasFile masFile = new MasFile(program.getSourceFile().toString());

        for (String name : program.getAgentNames()) {
            masFile.addAgentDefinition(agentDefinitionParser.parse(program.getAgentDefinition(name)));
        }

        return masFile;
    }
}
