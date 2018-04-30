package nl.tudelft.goalkeeper.parser.results.files.mas;

import nl.tudelft.goalkeeper.parser.results.files.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class representing a MAS.
 */
public final class Mas extends File {

    private List<AgentDefinition> definitions;

    /**
     * Creates a new File instance.
     * @param fileName fileName of the file.
     * @throws IOException Thrown when file could not be read.
     */
    public Mas(String fileName) throws IOException {
        super(fileName);
        definitions = new ArrayList<>();
    }

    /**
     * Gets all agent definitions in this mas.
     * @return All agent definitions in this mas.
     */
    public List<AgentDefinition> getAgentDefinitions() {
        return Collections.unmodifiableList(definitions);
    }

    /**
     * Adds an agent definition to this mas.
     * @param definition The definition to add.
     */
    public void addAgentDefinition(AgentDefinition definition) {
        definitions.add(definition);
    }

}
