package nl.tudelft.goalkeeper.parser.results.files.mas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the MasFile class.
 */
class MasFileTest {

    private MasFile mas;

    /**
     * Sets up the testing environment.
     */
    @BeforeEach
    void setup() throws IOException {
        mas = new MasFile("src/test/resources/testfiles/emptyfile.txt");
    }

    /**
     * Checks that the agent definitions are added correctly.
     */
    @Test
    void definitionsTest() {
        AgentDefinition a1 = Mockito.mock(AgentDefinition.class);
        AgentDefinition a2 = Mockito.mock(AgentDefinition.class);

        assertThat(mas.getAgentDefinitions()).isEmpty();
        mas.addAgentDefinition(a1);
        assertThat(mas.getAgentDefinitions()).containsExactly(a1);
        mas.addAgentDefinition(a2);
        assertThat(mas.getAgentDefinitions()).containsExactly(a1, a2);
    }
}
