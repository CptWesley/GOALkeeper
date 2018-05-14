package nl.tudelft.goalkeeper.parser.results.files.mas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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

    /**
     * Checks that having an init module is correctly reflected in the 'has' method.
     */
    @Test
    void initModuleTest() {
        ModuleUsageDefinition mud = Mockito.mock(ModuleUsageDefinition.class);
        assertThat(definition.hasInitModule()).isFalse();
        definition.setInitModule(mud);
        assertThat(definition.getInitModule()).isSameAs(mud);
        assertThat(definition.hasInitModule()).isTrue();
    }

    /**
     * Checks that having a main module is correctly reflected in the 'has' method.
     */
    @Test
    void mainModuleTest() {
        ModuleUsageDefinition mud = Mockito.mock(ModuleUsageDefinition.class);
        assertThat(definition.hasMainModule()).isFalse();
        definition.setMainModule(mud);
        assertThat(definition.getMainModule()).isSameAs(mud);
        assertThat(definition.hasMainModule()).isTrue();
    }

    /**
     * Checks that having an event module is correctly reflected in the 'has' method.
     */
    @Test
    void eventModuleTest() {
        ModuleUsageDefinition mud = Mockito.mock(ModuleUsageDefinition.class);
        assertThat(definition.hasEventModule()).isFalse();
        definition.setEventModule(mud);
        assertThat(definition.getEventModule()).isSameAs(mud);
        assertThat(definition.hasEventModule()).isTrue();
    }

    /**
     * Checks that having a shutdown module is correctly reflected in the 'has' method.
     */
    @Test
    void shutdownModuleTest() {
        ModuleUsageDefinition mud = Mockito.mock(ModuleUsageDefinition.class);
        assertThat(definition.hasShutdownModule()).isFalse();
        definition.setShutDownModule(mud);
        assertThat(definition.getShutDownModule()).isSameAs(mud);
        assertThat(definition.hasShutdownModule()).isTrue();
    }
}
