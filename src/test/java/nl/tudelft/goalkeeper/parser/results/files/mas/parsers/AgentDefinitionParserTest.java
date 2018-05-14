package nl.tudelft.goalkeeper.parser.results.files.mas.parsers;

import krTools.parser.SourceInfo;
import languageTools.program.agent.Module;
import languageTools.program.mas.AgentDefinition;
import languageTools.program.mas.UseClause;
import nl.tudelft.goalkeeper.checking.violations.source.Source;
import nl.tudelft.goalkeeper.checking.violations.source.SourceParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the AgentDefinitionParser class.
 */
class AgentDefinitionParserTest {

    private final static String NAME = "xzad138";
    private final static String SOURCE = "fg234eh";
    private AgentDefinitionParser parser;
    private AgentDefinition definition;
    private SourceInfo si;

    /**
     * Sets up the testing environment before each test.
     */
    @BeforeEach
    void setup() {
        parser = new AgentDefinitionParser();
        definition = Mockito.mock(AgentDefinition.class);
        Mockito.when(definition.getName()).thenReturn(NAME);
        si = Mockito.mock(SourceInfo.class);
        Mockito.when(si.toString()).thenReturn(SOURCE);
        Mockito.when(definition.getSourceInfo()).thenReturn(si);
    }

    /**
     * Checks that the name is set correctly.
     */
    @Test
    void nameTest() {
        assertThat(parser.parse(definition).getName()).isEqualTo(NAME);
    }

    /**
     * Checks that module usage definitions are set correctly.
     */
    @Test
    void moduleUsageTest() {
        Module module = Mockito.mock(Module.class);
        UseClause useClause = Mockito.mock(UseClause.class);
        Mockito.when(module.getSourceInfo()).thenReturn(si);

        SourceParser sourceParser = Mockito.mock(SourceParser.class);
        Source source = Mockito.mock(Source.class);
        Mockito.when(sourceParser.parse(Mockito.any())).thenReturn(source);
        parser.setSourceParser(sourceParser);

        Mockito.when(definition.getInitModule()).thenReturn(module);
        Mockito.when(definition.getInitUseClause()).thenReturn(useClause);
        Mockito.when(definition.getEventModule()).thenReturn(module);
        Mockito.when(definition.getEventUseClause()).thenReturn(useClause);
        Mockito.when(definition.getMainModule()).thenReturn(module);
        Mockito.when(definition.getMainUseClause()).thenReturn(useClause);
        Mockito.when(definition.getShutdownModule()).thenReturn(module);
        Mockito.when(definition.getShutdownUseClause()).thenReturn(useClause);

        nl.tudelft.goalkeeper.parser.results.files.mas.AgentDefinition result
                = parser.parse(definition);

        assertThat(result.hasInitModule()).isTrue();
        assertThat(result.hasMainModule()).isTrue();
        assertThat(result.hasEventModule()).isTrue();
        assertThat(result.hasShutdownModule()).isTrue();

        assertThat(result.getInitModule().getTarget()).isEqualTo(SOURCE);
        assertThat(result.getMainModule().getTarget()).isEqualTo(SOURCE);
        assertThat(result.getEventModule().getTarget()).isEqualTo(SOURCE);
        assertThat(result.getShutDownModule().getTarget()).isEqualTo(SOURCE);

        assertThat(result.getInitModule().getSource()).isSameAs(source);
        assertThat(result.getMainModule().getSource()).isSameAs(source);
        assertThat(result.getEventModule().getSource()).isSameAs(source);
        assertThat(result.getShutDownModule().getSource()).isSameAs(source);
    }
}
