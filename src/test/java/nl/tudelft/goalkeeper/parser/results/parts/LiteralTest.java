package nl.tudelft.goalkeeper.parser.results.parts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the Literal class.
 */
class LiteralTest {
    private static final String sig = "sig/2";
    private Variable var1, var2, var3;
    private Literal literal;

    /**
     * Sets up the testing environment before each test.
     */
    @BeforeEach
    void setup() {
        var1 = Mockito.mock(Variable.class);
        var2 = Mockito.mock(FreeVariable.class);
        var3 = Mockito.mock(BoundVariable.class);
        Variable[] vars = new Variable[] { var1, var2, var3 };
        literal = new Literal(sig, vars);
    }

    /**
     * Checks that the signature is returned correctly.
     */
    @Test
    void getSignatureTest() {
        assertThat(literal.getSignature()).isEqualTo(sig);
    }

    /**
     * Checks that variables are returned correctly.
     */
    @Test
    void getVariablesTest() {
        assertThat(literal.getVariables()[0]).isEqualTo(var1);
        assertThat(literal.getVariables()[1]).isEqualTo(var2);
        assertThat(literal.getVariables()[2]).isEqualTo(var3);
    }

    /**
     * Checks that object is equal to itself.
     */
    @Test
    void equalSelf() {
        assertThat(literal).isEqualTo(literal);
        assertThat(literal.hashCode()).isEqualTo(literal.hashCode());
    }

    /**
     * Checks that object is equal to a copy.
     */
    @Test
    void equalSame() {
        Variable[] vars = new Variable[] { var1, var2, var3 };
        Literal other = new Literal(sig, vars);
        assertThat(literal).isEqualTo(other);
        assertThat(literal.hashCode()).isEqualTo(other.hashCode());
    }

    /**
     * Checks that object is different from an object with different signature.
     */
    @Test
    void notEqualDifferentSig() {
        Variable[] vars = new Variable[] { var1, var2, var3 };
        Literal other = new Literal("gsfdgdsf", vars);
        assertThat(literal).isNotEqualTo(other);
    }

    /**
     * Checks that object is different from an object with different variable size.
     */
    @Test
    void notEqualDifferentSize() {
        Variable[] vars = new Variable[] { var1, var2 };
        Literal other = new Literal(sig, vars);
        assertThat(literal).isNotEqualTo(other);
    }

    /**
     * Checks that object is different from an object of different type.
     */
    @Test
    void notEqualDifferentType() {
        assertThat(literal).isNotEqualTo(3);
    }

    /**
     * Checks that object is different from null.
     */
    @Test
    void notEqualNull() {
        assertThat(literal).isNotEqualTo(null);
    }
}
