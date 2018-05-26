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
import swiprolog.language.PrologQuery;
import swiprolog.language.PrologVar;
import swiprolog.language.impl.PrologImplFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        expression = Mockito.mock(PrologCompound.class);
        Mockito.when(expression.getSignature()).thenReturn(NAME);
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
        PrologCompound expression = Mockito.mock(PrologCompound.class);
        Mockito.when(expression.getSignature()).thenReturn(NAME);
        Mockito.when(expression.iterator()).thenReturn(Collections.emptyIterator());
        Mockito.when(expression.getArity()).thenReturn(2);
        assertThat(parser.parse(expression)).isInstanceOf(Compound.class);
        assertThat(parser.parse(expression).getIdentifier()).isEqualTo(NAME);
    }

    /**
     * Checks that a compound has it's proper arguments.
     */
    @Test
    void compoundArgumentsTest() throws InvalidKRLanguageException {
        PrologCompound expression = Mockito.mock(PrologCompound.class);
        Mockito.when(expression.getSignature()).thenReturn(NAME);
        Term t1 = Mockito.mock(PrologVar.class);
        Term t2 = Mockito.mock(PrologVar.class);
        Mockito.when(t1.getSignature()).thenReturn("t1");
        Mockito.when(t2.getSignature()).thenReturn("t2");
        List<Term> args = Arrays.asList(t1, t2);
        Mockito.when(expression.iterator()).thenReturn(args.iterator());
        Mockito.when(expression.getArity()).thenReturn(2);
        Expression result = parser.parse(expression);
        assertThat(result).isInstanceOf(Compound.class);
        Compound compound = (Compound) result;
        assertThat(compound.getArguments()).hasSize(2);
        assertThat(compound.getArguments().get(0)).isInstanceOf(Variable.class);
        assertThat(compound.getArguments().get(0).getIdentifier()).isEqualTo("t1");
        assertThat(compound.getArguments().get(1)).isInstanceOf(Variable.class);
        assertThat(compound.getArguments().get(1).getIdentifier()).isEqualTo("t2");
    }

    /**
     * Checks that a query gets parsed like it's inner compound.
     */
    @Test
    void queryTest() throws InvalidKRLanguageException {
        PrologCompound expression = Mockito.mock(PrologCompound.class);
        Mockito.when(expression.getSignature()).thenReturn(NAME);
        Mockito.when(expression.iterator()).thenReturn(Collections.emptyIterator());
        PrologQuery query = Mockito.mock(PrologQuery.class);
        Mockito.when(query.getCompound()).thenReturn(expression);
        assertThat(parser.parse(query)).isInstanceOf(Constant.class);
        assertThat(parser.parse(query).getIdentifier()).isEqualTo(NAME);
        assertThat(parser.parse(query).getKRLanguage()).isEqualTo(KRLanguage.PROLOG);
    }

    /**
     * Checks that a we throw an exception when we don't know how to parse the type.
     */
    @Test
    void invalidTest() {
        PrologExpression expression = Mockito.mock(PrologExpression.class);
        assertThatThrownBy(() -> parser.parse(expression)).isInstanceOf(InvalidKRLanguageException.class);
    }

    /**
     * Checks that an integer is correctly parsed to a constant.
     */
    @Test
    void integrationIntTest() throws InvalidKRLanguageException {
        Expression res = parser.parse(PrologImplFactory.getNumber(42, null));
        assertThat(res).isInstanceOf(Constant.class);
    }

    /**
     * Checks that a float is correctly parsed to a constant.
     */
    @Test
    void integrationFloatTest() throws InvalidKRLanguageException {
        Expression res = parser.parse(PrologImplFactory.getNumber(0.5, null));
        assertThat(res).isInstanceOf(Constant.class);
    }

    /**
     * Checks that an atom is correctly parsed to a constant.
     */
    @Test
    void integrationAtomTest() throws InvalidKRLanguageException {
        Expression res = parser.parse(PrologImplFactory.getAtom(NAME, null));
        assertThat(res).isInstanceOf(Constant.class);
    }

    /**
     * Checks that a var is correctly parsed to a variable.
     */
    @Test
    void integrationVarTest() throws InvalidKRLanguageException {
        Expression res = parser.parse(PrologImplFactory.getVar(NAME, null));
        assertThat(res).isInstanceOf(Variable.class);
    }

    /**
     * Checks that a compound is correctly parsed to a compound.
     */
    @Test
    void integrationCompoundTest() throws InvalidKRLanguageException {
        Term[] args = Collections.singleton(PrologImplFactory.getAtom(NAME, null)).toArray(new Term[1]);
        Expression res = parser.parse(PrologImplFactory.getCompound(NAME, args, null));
        assertThat(res).isInstanceOf(Compound.class);
    }

    /**
     * Checks that a db formula is correctly parsed to an expression.
     */
    @Test
    void integrationDBFormulaTest() throws InvalidKRLanguageException {
        PrologCompound compound = PrologImplFactory.getCompound(NAME, new Term[0], null);
        Expression res = parser.parse(PrologImplFactory.getDBFormula(compound));
        assertThat(res).isInstanceOf(Constant.class);
    }

    /**
     * Checks that a query is correctly parsed to an expression.
     */
    @Test
    void integrationQueryTest() throws InvalidKRLanguageException {
        PrologCompound compound = PrologImplFactory.getCompound(NAME, new Term[0], null);
        Expression res = parser.parse(PrologImplFactory.getQuery(compound));
        assertThat(res).isInstanceOf(Constant.class);
    }

    /**
     * Checks that an update is correctly parsed to an expression.
     */
    @Test
    void integrationUpdateTest() throws InvalidKRLanguageException {
        PrologCompound compound = PrologImplFactory.getCompound(NAME, new Term[0], null);
        Expression res = parser.parse(PrologImplFactory.getUpdate(compound));
        assertThat(res).isInstanceOf(Constant.class);
    }
}
