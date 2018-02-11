package nl.tudelft.goalkeeper.parser.results.files.module.parsers;

import krTools.language.Term;
import krTools.language.Var;
import krTools.parser.SourceInfo;
import languageTools.program.agent.msc.MentalLiteral;
import languageTools.program.agent.selector.Selector;
import nl.tudelft.goalkeeper.checking.violations.source.CharacterSource;
import nl.tudelft.goalkeeper.exceptions.UnknownKRLanguageException;
import nl.tudelft.goalkeeper.parser.results.files.module.conditions.AGoalCondition;
import nl.tudelft.goalkeeper.parser.results.files.module.conditions.BeliefCondition;
import nl.tudelft.goalkeeper.parser.results.files.module.conditions.Condition;
import nl.tudelft.goalkeeper.parser.results.files.module.conditions.GoalACondition;
import nl.tudelft.goalkeeper.parser.results.files.module.conditions.GoalCondition;
import nl.tudelft.goalkeeper.parser.results.files.module.conditions.PerceptCondition;
import nl.tudelft.goalkeeper.parser.results.files.module.conditions.SentCondition;
import nl.tudelft.goalkeeper.parser.results.parts.Constant;
import nl.tudelft.goalkeeper.parser.results.parts.MessageMood;
import nl.tudelft.goalkeeper.parser.results.parts.Variable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import swiprolog.language.PrologQuery;

import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the condition parser class.
 */
class ConditionParserTest {

    private static final String PERCEPT = "percept";
    private static final String BELIEF = "bel";
    private static final String GOAL = "goal";
    private static final String AGOAL = "a-goal";
    private static final String GOALA = "goal-a";
    private static final String SENT_INDICATIVE = "sent:";
    private static final String SENT_IMPERATIVE = "sent!";
    private static final String SENT_INTERROGATIVE = "sent?";
    private static final String SIGNATURE = "Sig";

    private static final String FILE_NAME = "fgdsfgdfdsaw35";
    private static final int LINE_NUMBER = 102;
    private static final int CHARACTER_POSITION = 75;

    private MentalLiteral literal;
    private Selector selector;
    private PrologQuery query;
    private Term term;
    private org.jpl7.Term pTerm;

    /**
     * Sets up the testing environment before each test.
     */
    @BeforeEach
    void setup() {
        literal = Mockito.mock(MentalLiteral.class);
        selector = Mockito.mock(Selector.class);
        query = Mockito.mock(PrologQuery.class);
        term = Mockito.mock(Var.class);
        pTerm = Mockito.mock(org.jpl7.Term.class);

        Mockito.when(pTerm.isVariable()).thenReturn(true);
        Mockito.when(literal.getSelector()).thenReturn(selector);
        Mockito.when(literal.getFormula()).thenReturn(query);
        Mockito.when(query.getTerm()).thenReturn(pTerm);
        Mockito.when(selector.getParameters()).thenReturn(Collections.singletonList(term));
        Mockito.when(term.getSignature()).thenReturn(SIGNATURE);
    }

    /**
     * Tests that a percept condition is parsed correctly.
     */
    @Test
    void perceptTest() throws UnknownKRLanguageException {
        Mockito.when(literal.getOperator()).thenReturn(PERCEPT);
        assertThat(ConditionParser.parse(literal)).isInstanceOf(PerceptCondition.class);
    }

    /**
     * Tests that a belief condition is parsed correctly.
     */
    @Test
    void beliefTest() throws UnknownKRLanguageException {
        Mockito.when(literal.getOperator()).thenReturn(BELIEF);
        assertThat(ConditionParser.parse(literal)).isInstanceOf(BeliefCondition.class);
    }

    /**
     * Tests that a goal condition is parsed correctly.
     */
    @Test
    void goalTest() throws UnknownKRLanguageException {
        Mockito.when(literal.getOperator()).thenReturn(GOAL);
        assertThat(ConditionParser.parse(literal)).isInstanceOf(GoalCondition.class);
    }

    /**
     * Tests that a a-goal condition is parsed correctly.
     */
    @Test
    void aGoalTest() throws UnknownKRLanguageException {
        Mockito.when(literal.getOperator()).thenReturn(AGOAL);
        assertThat(ConditionParser.parse(literal)).isInstanceOf(AGoalCondition.class);
    }

    /**
     * Tests that a goal-a condition is parsed correctly.
     */
    @Test
    void goalATest() throws UnknownKRLanguageException {
        Mockito.when(literal.getOperator()).thenReturn(GOALA);
        assertThat(ConditionParser.parse(literal)).isInstanceOf(GoalACondition.class);
    }

    /**
     * Tests that an indicative sent condition is parsed correctly.
     */
    @Test
    void sentIndicativeTest() throws UnknownKRLanguageException {
        Mockito.when(literal.getOperator()).thenReturn(SENT_INDICATIVE);
        Condition c = ConditionParser.parse(literal);
        assertThat(c).isInstanceOf(SentCondition.class);
        SentCondition sc = (SentCondition) c;
        assertThat(sc.getMood()).isEqualTo(MessageMood.INDICATIVE);
        assertThat(sc.getSender()).isEqualTo(new Variable(SIGNATURE));
    }

    /**
     * Tests that an imperative sent condition is parsed correctly.
     */
    @Test
    void sentImperativeTest() throws UnknownKRLanguageException {
        Mockito.when(literal.getOperator()).thenReturn(SENT_IMPERATIVE);
        Condition c = ConditionParser.parse(literal);
        assertThat(c).isInstanceOf(SentCondition.class);
        SentCondition sc = (SentCondition) c;
        assertThat(sc.getMood()).isEqualTo(MessageMood.IMPERATIVE);
        assertThat(sc.getSender()).isEqualTo(new Variable(SIGNATURE));
    }

    /**
     * Tests that an interrogative sent condition is parsed correctly.
     */
    @Test
    void sentInterrogativeTest() throws UnknownKRLanguageException {
        Mockito.when(literal.getOperator()).thenReturn(SENT_INTERROGATIVE);
        Condition c = ConditionParser.parse(literal);
        assertThat(c).isInstanceOf(SentCondition.class);
        SentCondition sc = (SentCondition) c;
        assertThat(sc.getMood()).isEqualTo(MessageMood.INTERROGATIVE);
        assertThat(sc.getSender()).isEqualTo(new Variable(SIGNATURE));
    }

    /**
     * Checks that we can parse constant selectors as well.
     */
    @Test
    void constantSelectorTest() throws UnknownKRLanguageException {
        term = Mockito.mock(Term.class);
        Mockito.when(selector.getParameters()).thenReturn(Collections.singletonList(term));
        Mockito.when(term.getSignature()).thenReturn(SIGNATURE);
        Mockito.when(literal.getOperator()).thenReturn(SENT_INTERROGATIVE);
        SentCondition sc = (SentCondition) ConditionParser.parse(literal);
        assertThat(sc.getSender()).isEqualTo(new Constant(SIGNATURE));
    }

    /**
     * Checks that we don't crash when we don't have a selector.
     */
    @Test
    void noSelectorTest() throws UnknownKRLanguageException {
        Mockito.when(selector.getParameters()).thenReturn(new ArrayList<>());
        Mockito.when(literal.getOperator()).thenReturn(SENT_INTERROGATIVE);
        SentCondition sc = (SentCondition) ConditionParser.parse(literal);
        assertThat(sc.getSender()).isEqualTo(null);
    }

    /**
     * Checks that the source is parsed properly.
     */
    @Test
    void sourceTest() throws UnknownKRLanguageException {
        Mockito.when(literal.getOperator()).thenReturn(GOAL);
        SourceInfo si = Mockito.mock(SourceInfo.class);
        Mockito.when(si.getSource()).thenReturn(FILE_NAME);
        Mockito.when(si.getLineNumber()).thenReturn(LINE_NUMBER);
        Mockito.when(si.getCharacterPosition()).thenReturn(CHARACTER_POSITION);
        Mockito.when(literal.getSourceInfo()).thenReturn(si);
        CharacterSource source = (CharacterSource) ConditionParser.parse(literal).getSource();
        assertThat(source.getFile()).isEqualTo(FILE_NAME);
        assertThat(source.getLine()).isEqualTo(LINE_NUMBER);
        assertThat(source.getPosition()).isEqualTo(CHARACTER_POSITION);
    }
}
