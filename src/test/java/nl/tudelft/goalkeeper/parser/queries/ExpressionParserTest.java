package nl.tudelft.goalkeeper.parser.queries;

import jpl.Term;
import nl.tudelft.goalkeeper.parser.results.parts.Constant;
import nl.tudelft.goalkeeper.parser.results.parts.Expression;
import nl.tudelft.goalkeeper.parser.results.parts.Variable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the QueryParser class.
 */
class QueryParserTest {

    private static final String NAME = "ADFAASasdfase";
    private static final int INT_VALUE = 34;
    private static final float FLOAT_VALUE = 0.5f;
    private Term term;

    /**
     * Sets up the testing environment before each test.
     */
    @BeforeEach
    void setup() {
        term = Mockito.mock(Term.class);
        Mockito.when(term.name()).thenReturn(NAME);
    }

    /**
     * Checks that variables are created properly.
     */
    @Test
    void variableTest() {
        Mockito.when(term.isVariable()).thenReturn(true);
        Expression result = QueryParser.parse(term);
        assertThat(result).isInstanceOf(Variable.class);
        assertThat(result.getIdentifier()).isEqualTo(NAME);
    }

    /**
     * Checks that atoms are parsed properly.
     */
    @Test
    void atomTest() {
        Mockito.when(term.isAtom()).thenReturn(true);
        Expression result = QueryParser.parse(term);
        assertThat(result).isInstanceOf(Constant.class);
        assertThat(result.getIdentifier()).isEqualTo(NAME);
    }

    /**
     * Checks that integers are parsed properly.
     */
    @Test
    void integerTest() {
        Mockito.when(term.isInteger()).thenReturn(true);
        Mockito.when(term.intValue()).thenReturn(INT_VALUE);
        Expression result = QueryParser.parse(term);
        assertThat(result).isInstanceOf(Constant.class);
        assertThat(result.getIdentifier()).isEqualTo(INT_VALUE + "");
    }

    /**
     * Checks that floats are parsed properly.
     */
    @Test
    void floatTest() {
        Mockito.when(term.isFloat()).thenReturn(true);
        Mockito.when(term.floatValue()).thenReturn(FLOAT_VALUE);
        Expression result = QueryParser.parse(term);
        assertThat(result).isInstanceOf(Constant.class);
        assertThat(result.getIdentifier()).isEqualTo(FLOAT_VALUE + "");
    }
}
