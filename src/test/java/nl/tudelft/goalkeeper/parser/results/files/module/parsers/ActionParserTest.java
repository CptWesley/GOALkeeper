package nl.tudelft.goalkeeper.parser.results.files.module.parsers;

import nl.tudelft.goalkeeper.exceptions.InvalidKRLanguageException;
import krTools.parser.SourceInfo;
import languageTools.program.actionspec.UserSpecAction;
import languageTools.program.agent.Module;
import languageTools.program.agent.actions.ExitModuleAction;
import languageTools.program.agent.actions.ModuleCallAction;
import languageTools.program.agent.actions.NonMentalAction;
import languageTools.program.agent.actions.UserSpecCallAction;
import languageTools.program.agent.msg.SentenceMood;
import languageTools.program.agent.selector.Selector;
import nl.tudelft.goalkeeper.checking.violations.source.CharacterSource;
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
import swiprolog.language.PrologVar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the ActionParser class.
 */
class ActionParserTest {
    private static final String FILE_NAME = "fgadsfgd";
    private static final int LINE_NUMBER = 33;
    private static final int CHARACTER_POSITION = 12;

    private languageTools.program.agent.actions.Action input;
    private PrologTerm expression;

    private ActionParser parser;

    /**
     * Sets up the testing environment before each test.
     */
    private void setup(Class<? extends languageTools.program.agent.actions.Action> type) {
        parser = new ActionParser();
        expression = Mockito.mock(PrologVar.class);
        input = Mockito.mock(type);
        Mockito.when(input.getParameters()).thenReturn(Collections.singletonList(expression));
    }

    /**
     * Checks that we can parse a send action successfully.
     */
    @Test
    void sendActionTest()
            throws UnknownKRLanguageException, InvalidKRLanguageException {
        setup(languageTools.program.agent.actions.SendAction.class);
        languageTools.program.agent.actions.SendAction sa = (languageTools.program.agent.actions.SendAction) input;
        Selector selector = Mockito.mock(Selector.class);
        Mockito.when(selector.getParameters()).thenReturn(Collections.singletonList(expression));
        Mockito.when(sa.getSelector()).thenReturn(selector);
        Mockito.when((sa).getMood()).thenReturn(SentenceMood.IMPERATIVE);
        assertThat(parser.parse(input)).isInstanceOf(SendAction.class);
        assertThat(((SendAction)parser.parse(input)).getMood()).isEqualTo(MessageMood.IMPERATIVE);
        Mockito.when((sa).getMood()).thenReturn(SentenceMood.INDICATIVE);
        assertThat(((SendAction)parser.parse(input)).getMood()).isEqualTo(MessageMood.INDICATIVE);
        Mockito.when((sa).getMood()).thenReturn(SentenceMood.INTERROGATIVE);
        assertThat(((SendAction)parser.parse(input)).getMood()).isEqualTo(MessageMood.INTERROGATIVE);
    }

    /**
     * Checks that we can parse an exit action successfully.
     */
    @Test
    void exitActionTest()
            throws UnknownKRLanguageException, InvalidKRLanguageException {
        setup(ExitModuleAction.class);
        assertThat(parser.parse(input)).isInstanceOf(ExitAction.class);
    }

    /**
     * Checks that we can parse a start timer action successfully.
     */
    @Test
    void startTimerActionTest()
            throws UnknownKRLanguageException, InvalidKRLanguageException {
        setup(languageTools.program.agent.actions.StartTimerAction.class);
        Mockito.when(input.getParameters()).thenReturn(Arrays.asList(expression, expression, expression));
        assertThat(parser.parse(input)).isInstanceOf(StartTimerAction.class);
    }

    /**
     * Checks that we can parse a sub module action successfully.
     */
    @Test
    void subModuleActionTest()
            throws UnknownKRLanguageException, InvalidKRLanguageException {
        setup(ModuleCallAction.class);
        Mockito.when(input.getSignature()).thenReturn("null/5");
        Module module = Mockito.mock(Module.class);
        Mockito.when(module.getRules()).thenReturn(new ArrayList<>());
        Mockito.when(((ModuleCallAction)input).getTarget()).thenReturn(module);
        assertThat(parser.parse(input)).isInstanceOf(SubModuleAction.class);
    }

    /**
     * Checks that we can parse a module action successfully.
     */
    @Test
    void moduleActionTest()
            throws UnknownKRLanguageException, InvalidKRLanguageException {
        setup(ModuleCallAction.class);
        Mockito.when(input.getSignature()).thenReturn("nullifier/5");
        Module module = Mockito.mock(Module.class);
        Mockito.when(module.getRules()).thenReturn(new ArrayList<>());
        SourceInfo source = Mockito.mock(SourceInfo.class);
        Mockito.when(module.getSourceInfo()).thenReturn(source);
        Mockito.when(source.getSource()).thenReturn("AMAZING SOURCE");
        Mockito.when(((ModuleCallAction)input).getTarget()).thenReturn(module);
        assertThat(parser.parse(input)).isInstanceOf(ModuleAction.class);
        assertThat(((ModuleAction)parser.parse(input)).getTarget()).isEqualTo("AMAZING SOURCE");
    }

    /**
     * Checks that we can parse an external action successfully.
     */
    @Test
    void externalActionTest()
            throws UnknownKRLanguageException, InvalidKRLanguageException {
        setup(UserSpecCallAction.class);
        UserSpecAction spec = Mockito.mock(UserSpecAction.class);
        Mockito.when(((UserSpecCallAction)input).getSpecification()).thenReturn(spec);
        SourceInfo source = Mockito.mock(SourceInfo.class);
        Mockito.when(spec.getSourceInfo()).thenReturn(source);
        Mockito.when(source.getSource()).thenReturn("LAME SOURCE");
        assertThat(parser.parse(input)).isInstanceOf(ExternalAction.class);
        assertThat(((ExternalAction)parser.parse(input)).getTarget()).isEqualTo("LAME SOURCE");
    }

    /**
     * Checks that we can parse an internal action successfully.
     */
    @Test
    void internalActionTest()
            throws UnknownKRLanguageException, InvalidKRLanguageException {
        setup(NonMentalAction.class);
        Mockito.when(input.getSignature()).thenReturn("print/1");
        assertThat(parser.parse(input)).isInstanceOf(InternalAction.class);
        assertThat(parser.parse(input).getIdentifier()).isEqualTo("print/1");
        Mockito.when(input.getSignature()).thenReturn("log/1");
        assertThat(parser.parse(input)).isInstanceOf(InternalAction.class);
        assertThat(parser.parse(input).getIdentifier()).isEqualTo("log/1");
        //TODO: Remove the following lines when goal fixes the copy paste error.
        Mockito.when(input.getSignature()).thenReturn("starttimer/1");
        assertThat(parser.parse(input)).isInstanceOf(InternalAction.class);
        assertThat(parser.parse(input).getIdentifier()).isEqualTo("canceltimer/1");
    }

    /**
     * Checks if the source info is parsed correctly.
     */
    @Test
    void sourceInfoTest() throws UnknownKRLanguageException, InvalidKRLanguageException {
        setup(ExitModuleAction.class);
        SourceInfo si = Mockito.mock(SourceInfo.class);
        Mockito.when(si.getSource()).thenReturn(FILE_NAME);
        Mockito.when(si.getLineNumber()).thenReturn(LINE_NUMBER);
        Mockito.when(si.getCharacterPosition()).thenReturn(CHARACTER_POSITION);
        Mockito.when(input.getSourceInfo()).thenReturn(si);
        CharacterSource output = (CharacterSource) parser.parse(input).getSource();
        assertThat(output.getFile()).isEqualTo(FILE_NAME);
        assertThat(output.getLine()).isEqualTo(LINE_NUMBER);
        assertThat(output.getPosition()).isEqualTo(CHARACTER_POSITION);
    }
}
