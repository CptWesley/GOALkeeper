package nl.tudelft.goalkeeper.parser.results.files.module.parsers;

import jpl.Term;
import krTools.parser.SourceInfo;
import languageTools.program.actionspec.UserSpecAction;
import languageTools.program.agent.Module;
import languageTools.program.agent.actions.ExitModuleAction;
import languageTools.program.agent.actions.ModuleCallAction;
import languageTools.program.agent.actions.NonMentalAction;
import languageTools.program.agent.actions.UserSpecCallAction;
import languageTools.program.agent.msg.SentenceMood;
import languageTools.program.agent.selector.Selector;
import nl.tudelft.goalkeeper.exceptions.UnknownKRLanguageException;
import nl.tudelft.goalkeeper.parser.results.files.module.actions.ExitAction;
import nl.tudelft.goalkeeper.parser.results.files.module.actions.ExternalAction;
import nl.tudelft.goalkeeper.parser.results.files.module.actions.InternalAction;
import nl.tudelft.goalkeeper.parser.results.files.module.actions.ModuleAction;
import nl.tudelft.goalkeeper.parser.results.files.module.actions.SendAction;
import nl.tudelft.goalkeeper.parser.results.files.module.actions.StartTimerAction;
import nl.tudelft.goalkeeper.parser.results.files.module.actions.SubModuleAction;
import nl.tudelft.goalkeeper.parser.results.parts.MessageMood;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import swiprolog.language.PrologTerm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the ActionParser class.
 */
class ActionParserTest {
    private languageTools.program.agent.actions.Action input;
    private List parameters;
    private PrologTerm expression;

    /**
     * Sets up the testing environment before each test.
     */
    private void setup(Class<? extends languageTools.program.agent.actions.Action> type) {
        expression = Mockito.mock(PrologTerm.class);
        Term term = Mockito.mock(Term.class);
        Mockito.when(expression.getTerm()).thenReturn(term);
        Mockito.when(term.isVariable()).thenReturn(true);
        input = Mockito.mock(type);
        parameters = new LinkedList();
        Mockito.when(input.getParameters()).thenReturn(Collections.singletonList(expression));
    }

    /**
     * Checks that we can parse a send action successfully.
     */
    @Test
    void sendActionTest()
            throws UnknownKRLanguageException {
        setup(languageTools.program.agent.actions.SendAction.class);
        languageTools.program.agent.actions.SendAction sa = (languageTools.program.agent.actions.SendAction) input;
        Selector selector = Mockito.mock(Selector.class);
        Mockito.when(selector.getParameters()).thenReturn(Collections.singletonList(expression));
        Mockito.when(sa.getSelector()).thenReturn(selector);
        Mockito.when((sa).getMood()).thenReturn(SentenceMood.IMPERATIVE);
        assertThat(ActionParser.parse(input)).isInstanceOf(SendAction.class);
        assertThat(((SendAction)ActionParser.parse(input)).getMood()).isEqualTo(MessageMood.IMPERATIVE);
        Mockito.when((sa).getMood()).thenReturn(SentenceMood.INDICATIVE);
        assertThat(((SendAction)ActionParser.parse(input)).getMood()).isEqualTo(MessageMood.INDICATIVE);
        Mockito.when((sa).getMood()).thenReturn(SentenceMood.INTERROGATIVE);
        assertThat(((SendAction)ActionParser.parse(input)).getMood()).isEqualTo(MessageMood.INTERROGATIVE);
    }

    /**
     * Checks that we can parse an exit action successfully.
     */
    @Test
    void exitActionTest()
            throws UnknownKRLanguageException {
        setup(ExitModuleAction.class);
        assertThat(ActionParser.parse(input)).isInstanceOf(ExitAction.class);
    }

    /**
     * Checks that we can parse a start timer action successfully.
     */
    @Test
    void startTimerActionTest()
            throws UnknownKRLanguageException {
        setup(languageTools.program.agent.actions.StartTimerAction.class);
        Mockito.when(input.getParameters()).thenReturn(Arrays.asList(expression, expression, expression));
        assertThat(ActionParser.parse(input)).isInstanceOf(StartTimerAction.class);
    }

    /**
     * Checks that we can parse a sub module action successfully.
     */
    @Test
    void subModuleActionTest()
            throws UnknownKRLanguageException {
        setup(ModuleCallAction.class);
        Mockito.when(input.getSignature()).thenReturn("null/5");
        Module module = Mockito.mock(Module.class);
        Mockito.when(module.getRules()).thenReturn(new ArrayList<>());
        Mockito.when(((ModuleCallAction)input).getTarget()).thenReturn(module);
        assertThat(ActionParser.parse(input)).isInstanceOf(SubModuleAction.class);
    }

    /**
     * Checks that we can parse a module action successfully.
     */
    @Test
    void moduleActionTest()
            throws UnknownKRLanguageException {
        setup(ModuleCallAction.class);
        Mockito.when(input.getSignature()).thenReturn("nullifier/5");
        Module module = Mockito.mock(Module.class);
        Mockito.when(module.getRules()).thenReturn(new ArrayList<>());
        SourceInfo source = Mockito.mock(SourceInfo.class);
        Mockito.when(module.getSourceInfo()).thenReturn(source);
        Mockito.when(source.getSource()).thenReturn("AMAZING SOURCE");
        Mockito.when(((ModuleCallAction)input).getTarget()).thenReturn(module);
        assertThat(ActionParser.parse(input)).isInstanceOf(ModuleAction.class);
        assertThat(((ModuleAction)ActionParser.parse(input)).getTarget()).isEqualTo("AMAZING SOURCE");
    }

    /**
     * Checks that we can parse an external action successfully.
     */
    @Test
    void externalActionTest()
            throws UnknownKRLanguageException {
        setup(UserSpecCallAction.class);
        UserSpecAction spec = Mockito.mock(UserSpecAction.class);
        Mockito.when(((UserSpecCallAction)input).getSpecification()).thenReturn(spec);
        SourceInfo source = Mockito.mock(SourceInfo.class);
        Mockito.when(spec.getSourceInfo()).thenReturn(source);
        Mockito.when(source.getSource()).thenReturn("LAME SOURCE");
        assertThat(ActionParser.parse(input)).isInstanceOf(ExternalAction.class);
        assertThat(((ExternalAction)ActionParser.parse(input)).getTarget()).isEqualTo("LAME SOURCE");
    }

    /**
     * Checks that we can parse an internal action successfully.
     */
    @Test
    void internalActionTest()
            throws UnknownKRLanguageException {
        setup(NonMentalAction.class);
        Mockito.when(input.getSignature()).thenReturn("print/1");
        assertThat(ActionParser.parse(input)).isInstanceOf(InternalAction.class);
        assertThat(ActionParser.parse(input).getIdentifier()).isEqualTo("print/1");
        Mockito.when(input.getSignature()).thenReturn("log/1");
        assertThat(ActionParser.parse(input)).isInstanceOf(InternalAction.class);
        assertThat(ActionParser.parse(input).getIdentifier()).isEqualTo("log/1");
        //TODO: Remove the following lines when goal fixes the copy paste error.
        Mockito.when(input.getSignature()).thenReturn("starttimer/1");
        assertThat(ActionParser.parse(input)).isInstanceOf(InternalAction.class);
        assertThat(ActionParser.parse(input).getIdentifier()).isEqualTo("canceltimer/1");
    }
}