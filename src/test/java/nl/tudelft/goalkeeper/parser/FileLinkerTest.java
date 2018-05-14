package nl.tudelft.goalkeeper.parser;

import nl.tudelft.goalkeeper.checking.violations.source.Source;
import nl.tudelft.goalkeeper.parser.results.ParseResult;
import nl.tudelft.goalkeeper.parser.results.files.actionspec.ActionSpecFile;
import nl.tudelft.goalkeeper.parser.results.files.mas.AgentDefinition;
import nl.tudelft.goalkeeper.parser.results.files.mas.MasFile;
import nl.tudelft.goalkeeper.parser.results.files.mas.ModuleUsageDefinition;
import nl.tudelft.goalkeeper.parser.results.files.module.ModuleFile;
import nl.tudelft.goalkeeper.parser.results.files.module.ModuleRule;
import nl.tudelft.goalkeeper.parser.results.files.module.SubModule;
import nl.tudelft.goalkeeper.parser.results.files.module.actions.ExternalAction;
import nl.tudelft.goalkeeper.parser.results.files.module.actions.ModuleAction;
import nl.tudelft.goalkeeper.parser.results.files.module.actions.SubModuleAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the FileLinker class.
 */
class FileLinkerTest {

    private static final String SOURCE1 = "1234jd0n243m9d782";
    private static final String SOURCE2 = "trwevwt";
    private static final String SOURCE3 = "d425";
    private static final String SOURCE4 = "g3246";

    private ParseResult result;
    private FileLinker linker;
    private ModuleFile module;
    private ActionSpecFile action;
    private Source s1, s2;

    /**
     * Sets up the testing environment before each test.
     */
    @BeforeEach
    void setup() {
        linker = Mockito.spy(new FileLinker());

        s1 = Mockito.mock(Source.class);
        Mockito.when(s1.getFile()).thenReturn(SOURCE1);
        module = Mockito.mock(ModuleFile.class);
        Mockito.when(module.getSource()).thenReturn(s1);

        s2 = Mockito.mock(Source.class);
        Mockito.when(s2.getFile()).thenReturn(SOURCE2);
        action = Mockito.mock(ActionSpecFile.class);
        Mockito.when(action.getSource()).thenReturn(s2);

        result = Mockito.mock(ParseResult.class);
        Mockito.when(result.getModules()).thenReturn(Collections.singletonList(module));
        Mockito.when(result.getActionSpecs()).thenReturn(Collections.singletonList(action));
    }

    /**
     * Checks that we can create a module map correctly.
     */
    @Test
    void moduleMapTest() {
        assertThat(linker.createModuleMap(result).containsKey(SOURCE1)).isTrue();
        assertThat(linker.createModuleMap(result).get(SOURCE1)).isSameAs(module);
    }

    /**
     * Checks that we can create an action map correctly.
     */
    @Test
    void actionMapTest() {
        assertThat(linker.createActionSpecMap(result).containsKey(SOURCE2)).isTrue();
        assertThat(linker.createActionSpecMap(result).get(SOURCE2)).isSameAs(action);
    }

    /**
     * Checks that we can link mas files correctly.
     */
    @Test
    void linkMasTest() {
        MasFile mas = Mockito.mock(MasFile.class);
        AgentDefinition agent = Mockito.mock(AgentDefinition.class);
        Mockito.when(mas.getAgentDefinitions()).thenReturn(Collections.singletonList(agent));
        ModuleUsageDefinition md1 = Mockito.mock(ModuleUsageDefinition.class);
        ModuleUsageDefinition md2 = Mockito.mock(ModuleUsageDefinition.class);
        ModuleUsageDefinition md3 = Mockito.mock(ModuleUsageDefinition.class);
        ModuleUsageDefinition md4 = Mockito.mock(ModuleUsageDefinition.class);
        Mockito.when(md1.getTarget()).thenReturn(SOURCE1);
        Mockito.when(md2.getTarget()).thenReturn(SOURCE2);
        Mockito.when(md3.getTarget()).thenReturn(SOURCE3);
        Mockito.when(md4.getTarget()).thenReturn(SOURCE4);
        Mockito.when(agent.getEventModule()).thenReturn(md1);
        Mockito.when(agent.getInitModule()).thenReturn(md2);
        Mockito.when(agent.getMainModule()).thenReturn(md3);
        Mockito.when(agent.getShutDownModule()).thenReturn(md4);
        ModuleFile m1 = Mockito.mock(ModuleFile.class);
        ModuleFile m2 = Mockito.mock(ModuleFile.class);
        ModuleFile m3 = Mockito.mock(ModuleFile.class);
        ModuleFile m4 = Mockito.mock(ModuleFile.class);
        Map<String, ModuleFile> map = new HashMap<>();
        map.put(SOURCE1, m1);
        map.put(SOURCE2, m2);
        map.put(SOURCE3, m3);
        map.put(SOURCE4, m4);
        linker.linkMas(mas, map);
        Mockito.verify(md1, Mockito.times(1)).setModule(m1);
        Mockito.verify(md2, Mockito.times(1)).setModule(m2);
        Mockito.verify(md3, Mockito.times(1)).setModule(m3);
        Mockito.verify(md4, Mockito.times(1)).setModule(m4);
    }

    /**
     * Checks that we can link module files correctly.
     */
    @Test
    void linkModuleTest() {
        ExternalAction a1 = Mockito.mock(ExternalAction.class);
        ModuleAction a2 = Mockito.mock(ModuleAction.class);
        SubModuleAction a3 = Mockito.mock(SubModuleAction.class);

        Map<String, ModuleFile> mm = new HashMap<>();
        mm.put(SOURCE1, module);
        Map<String, ActionSpecFile> ma = new HashMap<>();
        ma.put(SOURCE2, action);

        SubModule sm = Mockito.mock(SubModule.class);

        ModuleRule rule = Mockito.mock(ModuleRule.class);
        Mockito.when(rule.getActions()).thenReturn(Arrays.asList(a1, a2, a3));
        Mockito.when(module.getRules()).thenReturn(Collections.singletonList(rule));

        Mockito.when(a1.getTarget()).thenReturn(SOURCE2);
        Mockito.when(a2.getTarget()).thenReturn(SOURCE1);
        Mockito.when(a3.getModule()).thenReturn(sm);

        linker.linkModule(module, mm, ma);
        Mockito.verify(a1, Mockito.times(1)).setAction(action);
        Mockito.verify(a2, Mockito.times(1)).setModule(module);
        Mockito.verify(sm, Mockito.times(1)).getRules();
    }

    /**
     * Checks that we link everything correctly.
     */
    @Test
    void linkTest() {
        ParseResult result = Mockito.mock(ParseResult.class);
        MasFile mas = Mockito.mock(MasFile.class);

        Mockito.when(result.getMasFile()).thenReturn(mas);
        Mockito.when(result.getModules()).thenReturn(Collections.singletonList(module));
        Mockito.when(result.getActionSpecs()).thenReturn(Collections.singletonList(action));

        linker.link(result);
        Mockito.verify(result, Mockito.times(1)).getMasFile();
        Mockito.verify(result, Mockito.times(2)).getModules();
        Mockito.verify(result, Mockito.times(1)).getActionSpecs();
    }
}
