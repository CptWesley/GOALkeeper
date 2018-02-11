package nl.tudelft.goalkeeper.parser.results.files.actionspec.parsers;

import krTools.language.Update;
import languageTools.program.actionspec.ActionPostCondition;
import languageTools.program.actionspec.ActionPreCondition;
import languageTools.program.actionspec.UserSpecAction;
import nl.tudelft.goalkeeper.exceptions.UnknownKRLanguageException;
import nl.tudelft.goalkeeper.parser.queries.ExpressionParser;
import nl.tudelft.goalkeeper.parser.results.files.actionspec.ActionSpecification;
import org.jpl7.Term;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import swiprolog.language.PrologQuery;
import swiprolog.language.PrologTerm;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the ActionSpecificationParser class.
 */
class ActionSpecificationParserTest {

    private static final String NAME = "fgdsfgdsu 432 ";

    private UserSpecAction action;
    private PrologQuery prologQuery;

    /**
     * Sets up the testing environment before each test.
     */
    @BeforeEach
    void setup() {
        prologQuery = Mockito.mock(PrologQuery.class);
        Term term = Mockito.mock(Term.class);
        Mockito.when(term.isVariable()).thenReturn(true);
        Mockito.when(prologQuery.getTerm()).thenReturn(term);
        action = Mockito.mock(UserSpecAction.class);
        Mockito.when(action.getName()).thenReturn(NAME);
        ActionPreCondition fullPreCondition = Mockito.mock(ActionPreCondition.class);
        Mockito.when(action.getFullPreCondition()).thenReturn(fullPreCondition);
        Mockito.when(fullPreCondition.getPreCondition()).thenReturn(prologQuery);
        ActionPostCondition actionPostCondition = Mockito.mock(ActionPostCondition.class);
        Mockito.when(action.getPositivePostcondition()).thenReturn(actionPostCondition);
        Update update = Mockito.mock(Update.class);
        Mockito.when(actionPostCondition.getPostCondition()).thenReturn(update);
        Mockito.when(update.toQuery()).thenReturn(prologQuery);
        Mockito.when(action.getParameters()).thenReturn(new ArrayList<>());
    }

    /**
     * Checks that the name is parsed correctly.
     */
    @Test
    void getNameTest() {
        assertThat(ActionSpecificationParser.parse(action).getName()).isEqualTo(NAME);
    }

    /**
     * Checks that the pre is set correctly.
     */
    @Test
    void getPreTest() throws UnknownKRLanguageException {
        assertThat(ActionSpecificationParser.parse(action).getPre())
                .isEqualTo(ExpressionParser.parse(prologQuery));
    }

    /**
     * Checks that the post is set correctly.
     */
    @Test
    void getPostTest() throws UnknownKRLanguageException {
        assertThat(ActionSpecificationParser.parse(action).getPost())
                .isEqualTo(ExpressionParser.parse(prologQuery));
    }

    /**
     * Checks that the parameters are correctly empty.
     */
    @Test
    void getParametersEmptyTest() {
        assertThat(ActionSpecificationParser.parse(action).getParameters()).isEmpty();
    }

    /**
     * Checks that parameters are retrieved correctly.
     */
    @Test
    void getParametersTest() throws UnknownKRLanguageException {
        PrologTerm prologTerm = Mockito.mock(PrologTerm.class);
        Term term = Mockito.mock(Term.class);
        Mockito.when(term.isVariable()).thenReturn(true);
        Mockito.when(prologTerm.getTerm()).thenReturn(term);
        Mockito.when(action.getParameters()).thenReturn(Arrays.asList(prologTerm, prologTerm));
        ActionSpecification result = ActionSpecificationParser.parse(action);
        assertThat(result.getParameters()).hasSize(2);
        assertThat(result.getParameters())
                .containsExactly(ExpressionParser.parse(prologTerm),
                        ExpressionParser.parse(prologTerm));
    }
}
