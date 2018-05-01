package nl.tudelft.goalkeeper.parser.results.files.mas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the ModuleUsageDefinition class.
 */
class ModuleUsageDefinitionTest {
    private final static String TARGET = "ffgds134fdsg";

    private ModuleUsageDefinition definition;

    /**
     * Sets up the testing environment before each test.
     */
    @BeforeEach
    void setup() {
        definition = new ModuleUsageDefinition(TARGET);
    }

    /**
     * Checks that the target is correctly set.
     */
    @Test
    void getTargetTest() {
        assertThat(definition.getTarget()).isEqualTo(TARGET);
    }
}
