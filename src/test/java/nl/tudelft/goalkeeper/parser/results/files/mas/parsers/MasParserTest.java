package nl.tudelft.goalkeeper.parser.results.files.mas.parsers;

import languageTools.program.mas.MASProgram;
import nl.tudelft.goalkeeper.parser.results.files.mas.AgentDefinition;
import nl.tudelft.goalkeeper.parser.results.files.mas.MasFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the MasParser class.
 */
class MasParserTest {

    private static final String SOURCE = "src/test/resources/testfiles/emptyfile.txt";

    private MasParser parser;
    private MASProgram program;

    /**
     * Sets up the testing environment.
     */
    @BeforeEach
    void setup() {
        parser = new MasParser();
        program = Mockito.mock(MASProgram.class);
        File file = new File(SOURCE);
        Mockito.when(program.getSourceFile()).thenReturn(file);
    }

    /**
     * Checks that a mas file without definitions contains no definitions.
     */
    @Test
    void emptyTest() throws IOException {
        MasFile mf = parser.parse(program);
        assertThat(mf.getAgentDefinitions()).isEmpty();
    }

    /**
     * Checks that a mas file with definitions contains definitions.
     */
    @Test
    void notEmptyTest() throws IOException {
        AgentDefinitionParser ap = Mockito.mock(AgentDefinitionParser.class);
        parser.setAgentDefinitionParser(ap);
        AgentDefinition a1 = Mockito.mock(AgentDefinition.class);
        AgentDefinition a2 = Mockito.mock(AgentDefinition.class);
        Mockito.when(ap.parse(Mockito.any())).thenReturn(a1, a2);
        Set<String> names = new HashSet<>();
        names.add("a");
        names.add("b");
        Mockito.when(program.getAgentNames()).thenReturn(names);
        Mockito.when(program.getAgentDefinition(Mockito.any())).thenReturn(null);

        MasFile mf = parser.parse(program);
        assertThat(mf.getAgentDefinitions()).containsExactly(a1, a2);
    }
}
