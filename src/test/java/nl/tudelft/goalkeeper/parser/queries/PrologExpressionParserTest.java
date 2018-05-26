package nl.tudelft.goalkeeper.parser.queries;

import krTools.language.Term;
import nl.tudelft.goalkeeper.exceptions.InvalidKRLanguageException;
import nl.tudelft.goalkeeper.parser.results.parts.Compound;
import nl.tudelft.goalkeeper.parser.results.parts.Constant;
import nl.tudelft.goalkeeper.parser.results.parts.Expression;
import nl.tudelft.goalkeeper.parser.results.parts.KRLanguage;
import nl.tudelft.goalkeeper.parser.results.parts.Variable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import swiprolog.language.PrologCompound;
import swiprolog.language.PrologExpression;
import swiprolog.language.PrologVar;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the PrologExpressionParser class.
 */
class PrologExpressionParserTest {

    private static final String NAME = "fdfgdsfgds";

    private PrologExpressionParser parser;
    private PrologExpression expression;

    /**
     * Sets up the testing environment before each test.
     */
    @BeforeEach
    void setup() {
        parser = new PrologExpressionParser();
        expression = Mockito.mock(PrologVar.class);
        Mockito.when(expression.getSignature()).thenReturn(NAME);
    }

    /**
     * Checks that the KRLanguage is Prolog.
     */
    @Test
    void getLanguageTest() throws InvalidKRLanguageException {
        assertThat(parser.parse(expression).getKRLanguage()).isEqualTo(KRLanguage.PROLOG);
    }

    /**
     * Checks that the name of the expression is correct.
     */
    @Test
    void getNameTest() throws InvalidKRLanguageException {
        assertThat(parser.parse(expression).getIdentifier()).isEqualTo(NAME);
    }

    /**
     * Checks that an integer is parsed to a constant.
     */
    @Test
    void constantTest() throws InvalidKRLanguageException {
        Mockito.when(expression.isClosed()).thenReturn(true);
        assertThat(parser.parse(expression)).isInstanceOf(Constant.class);
        assertThat(parser.parse(expression).getIdentifier()).isEqualTo(NAME);
    }

    /**
     * Checks that an integer is parsed to a constant.
     */
    @Test
    void variableTest() throws InvalidKRLanguageException {
        Mockito.when(expression.isClosed()).thenReturn(false);
        assertThat(parser.parse(expression)).isInstanceOf(Variable.class);
        assertThat(parser.parse(expression).getIdentifier()).isEqualTo(NAME);
    }

    /**
     * Checks that a compound is parsed to a constant.
     */
    @Test
    void compoundTest() throws InvalidKRLanguageException {
        expression = Mockito.mock(PrologCompound.class);
        assertThat(parser.parse(expression)).isInstanceOf(Compound.class);
        assertThat(parser.parse(expression).getIdentifier()).isEqualTo(NAME);
    }

    /**
     * Checks that a function has it's proper arguments.
     */
    @Test
    void compoundArgumentsTest() throws InvalidKRLanguageException {
        expression = Mockito.mock(PrologCompound.class);
        assertThat(parser.parse(expression)).isInstanceOf(Compound.class);
        assertThat(parser.parse(expression).getIdentifier()).isEqualTo(NAME);
        Term t1 = Mockito.mock(PrologVar.class);
        Term t2 = Mockito.mock(PrologVar.class);
        Mockito.when(t1.getSignature()).thenReturn("t1");
        Mockito.when(t2.getSignature()).thenReturn("t2");
        /*
        Mockito.when(expression.args()).thenReturn(new Term[] { t1, t2 });
        Expression result = parser.parse(query);
        assertThat(result).isInstanceOf(Compound.class);
        Compound compound = (Compound) result;
        assertThat(compound.getArguments()).hasSize(2);
        assertThat(compound.getArguments().get(0)).isInstanceOf(Variable.class);
        assertThat(compound.getArguments().get(0).getIdentifier()).isEqualTo("t1" + ZERO_ANARY);
        assertThat(compound.getArguments().get(1)).isInstanceOf(Constant.class);
        assertThat(compound.getArguments().get(1).getIdentifier()).isEqualTo("t2" + ZERO_ANARY);
        */
    }
}
