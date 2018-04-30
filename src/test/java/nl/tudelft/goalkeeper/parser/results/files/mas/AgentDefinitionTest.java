package nl.tudelft.goalkeeper.parser.results.files.mas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the AgentDefinition class.
 */
class AgentDefinitionTest {

    private final static String NAME = "342j ctgdf";
    private AgentDefinition definition;

    /**
     * Sets up the testing environment before each test.
     */
    @BeforeEach
    void setup() {
        definition = new AgentDefinition(NAME);
    }

    /**
     * Checks that the name is correctly set.
     */
    @Test
    void nameTest() {
       assertThat(definition.getName()).isEqualTo(NAME);
    }
}
