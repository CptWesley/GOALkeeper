package nl.tudelft.goalkeeper.parser.queries;

import nl.tudelft.goalkeeper.parser.results.parts.Compound;
import org.jpl7.Term;
import nl.tudelft.goalkeeper.parser.results.parts.Constant;
import nl.tudelft.goalkeeper.parser.results.parts.Expression;
import nl.tudelft.goalkeeper.parser.results.parts.KRLanguage;
import nl.tudelft.goalkeeper.parser.results.parts.Variable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import swiprolog.language.PrologQuery;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the PrologExpressionParser class.
 */
class PrologExpressionParserTest {

    private static final String NAME = "fdfgdsfgds";
    private static final String ZERO_ANARY = "/0";
    private static final int INT_VALUE = 33;
    private static final float FLOAT_VALUE = 0.5f;

    private PrologExpressionParser parser;
    private PrologQuery query;
    private Term term;

    /**
     * Sets up the testing environment before each test.
     */
    @BeforeEach
    void setup() {
        parser = new PrologExpressionParser();
        query = Mockito.mock(PrologQuery.class);
        term = Mockito.mock(Term.class);
        Mockito.when(query.getTerm()).thenReturn(term);
        Mockito.when(term.args()).thenReturn(new Term[0]);
        Mockito.when(term.name()).thenReturn(NAME);
        Mockito.when(term.intValue()).thenReturn(INT_VALUE);
        Mockito.when(term.longValue()).thenReturn((long)INT_VALUE);
        Mockito.when(term.floatValue()).thenReturn(FLOAT_VALUE);
    }

    /**
     * Checks that the KRLanguage is Prolog.
     */
    @Test
    void getLanguageTest() {
        assertThat(parser.parse(query).getKRLanguage()).isEqualTo(KRLanguage.PROLOG);
    }

    /**
     * Checks that the name of the expression is correct.
     */
    @Test
    void getNameTest() {
        assertThat(parser.parse(query).getIdentifier()).isEqualTo(term.name() + ZERO_ANARY);
    }

    /**
     * Checks that an integer is parsed to a constant.
     */
    @Test
    void integerTest() {
        Mockito.when(term.isInteger()).thenReturn(true);
        assertThat(parser.parse(query)).isInstanceOf(Constant.class);
        assertThat(parser.parse(query).getIdentifier()).isEqualTo(INT_VALUE + ZERO_ANARY);
    }

    /**
     * Checks that a float is parsed to a constant.
     */
    @Test
    void floatTest() {
        Mockito.when(term.isFloat()).thenReturn(true);
        assertThat(parser.parse(query)).isInstanceOf(Constant.class);
        assertThat(parser.parse(query).getIdentifier()).isEqualTo(FLOAT_VALUE + ZERO_ANARY);
    }

    /**
     * Checks that an atom is parsed to a constant.
     */
    @Test
    void atomTest() {
        Mockito.when(term.isAtom()).thenReturn(true);
        assertThat(parser.parse(query)).isInstanceOf(Constant.class);
        assertThat(parser.parse(query).getIdentifier()).isEqualTo(NAME + ZERO_ANARY);
    }

    /**
     * Checks that a variable is parsed to a constant.
     */
    @Test
    void variableTest() {
        Mockito.when(term.isVariable()).thenReturn(true);
        assertThat(parser.parse(query)).isInstanceOf(Variable.class);
        assertThat(parser.parse(query).getIdentifier()).isEqualTo(NAME + ZERO_ANARY);
    }

    /**
     * Checks that a compound is parsed to a constant.
     */
    @Test
    void compoundTest() {
        Mockito.when(term.isCompound()).thenReturn(true);
        assertThat(parser.parse(query)).isInstanceOf(Compound.class);
        assertThat(parser.parse(query).getIdentifier()).isEqualTo(NAME + ZERO_ANARY);
    }

    /**
     * Checks that a function has it's proper arguments.
     */
    @Test
    void compoundArgumentsTest() {
        Mockito.when(term.isCompound()).thenReturn(true);
        Term t1 = Mockito.mock(Term.class);
        Term t2 = Mockito.mock(Term.class);
        Mockito.when(t1.isVariable()).thenReturn(true);
        Mockito.when(t2.isAtom()).thenReturn(true);
        Mockito.when(t1.name()).thenReturn("t1");
        Mockito.when(t2.name()).thenReturn("t2");
        Mockito.when(term.args()).thenReturn(new Term[] { t1, t2 });
        Expression result = parser.parse(query);
        assertThat(result).isInstanceOf(Compound.class);
        Compound compound = (Compound) result;
        assertThat(compound.getArguments()).hasSize(2);
        assertThat(compound.getArguments().get(0)).isInstanceOf(Variable.class);
        assertThat(compound.getArguments().get(0).getIdentifier()).isEqualTo("t1" + ZERO_ANARY);
        assertThat(compound.getArguments().get(1)).isInstanceOf(Constant.class);
        assertThat(compound.getArguments().get(1).getIdentifier()).isEqualTo("t2" + ZERO_ANARY);
    }
}
