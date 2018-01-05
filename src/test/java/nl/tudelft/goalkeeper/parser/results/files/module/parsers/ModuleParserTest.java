package nl.tudelft.goalkeeper.parser.results.files.module.parsers;

import languageTools.program.agent.Module;
import languageTools.program.agent.actions.ActionCombo;
import languageTools.program.agent.msc.MentalStateCondition;
import languageTools.program.agent.rules.Rule;
import nl.tudelft.goalkeeper.parser.results.files.module.ModuleFile;
import nl.tudelft.goalkeeper.parser.results.files.module.SubModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the ModuleParser class.
 */
class ModuleParserTest {

    private static final String SOURCE = "src/test/resources/testfiles/emptyfile.txt";

    private Module module;

    /**
     * Sets up the testing environment before each test.
     */
    @BeforeEach
    void setup() {
        module = Mockito.mock(Module.class);
        File file = new File(SOURCE);
        Mockito.when(module.getSourceFile()).thenReturn(file);
    }

    /**
     * Checks that a module without rules contains no rules.
     */
    @Test
    void emptyTest() throws IOException {
        ModuleFile mf = ModuleParser.parseToFile(module);
        assertThat(mf.getRules()).isEmpty();
    }

    /**
     * Checks that we can parse a module file with a rule.
     */
    @Test
    void withRuleTest() throws IOException {
        Rule rule = Mockito.mock(Rule.class);
        MentalStateCondition msc = Mockito.mock(MentalStateCondition.class);
        ActionCombo ac = Mockito.mock(ActionCombo.class);
        Mockito.when(module.getRules()).thenReturn(Collections.singletonList(rule));
        Mockito.when(rule.getCondition()).thenReturn(msc);
        Mockito.when(rule.getAction()).thenReturn(ac);
        Mockito.when(msc.getAllLiterals()).thenReturn(new ArrayList<>());
        Mockito.when(ac.getActions()).thenReturn(new ArrayList<>());
        ModuleFile mf = ModuleParser.parseToFile(module);
        assertThat(mf.getRules()).hasSize(1);
    }

    /**
     * Checks that we can parse a submodule with a rule.
     */
    @Test
    void submoduleTest() {
        Rule rule = Mockito.mock(Rule.class);
        MentalStateCondition msc = Mockito.mock(MentalStateCondition.class);
        ActionCombo ac = Mockito.mock(ActionCombo.class);
        Mockito.when(module.getRules()).thenReturn(Collections.singletonList(rule));
        Mockito.when(rule.getCondition()).thenReturn(msc);
        Mockito.when(rule.getAction()).thenReturn(ac);
        Mockito.when(msc.getAllLiterals()).thenReturn(new ArrayList<>());
        Mockito.when(ac.getActions()).thenReturn(new ArrayList<>());
        SubModule mf = ModuleParser.parseToSubModule(module);
        assertThat(mf.getRules()).hasSize(1);
    }

    /**
     * Checks if we can set and retrieve the name correctly.
     */
    @Test
    void getNameTest() throws IOException {
        Mockito.when(module.getName()).thenReturn("WAF");
        ModuleFile mf = ModuleParser.parseToFile(module);
        assertThat(mf.getName()).isEqualTo("WAF");
    }
}
