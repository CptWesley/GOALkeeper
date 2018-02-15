package nl.tudelft.goalkeeper.parser.results.parts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the Function class.
 */
class FunctionTest extends ExpressionTest {

    private static final String identifier = "DGFDS";

    private Function function;
    private Expression p1, p2;

    /**
     * {@inheritDoc}
     */
    @Override
    Expression getInstance() {
        return function;
    }

    /**
     * Sets up the testing environment before each test.
     */
    @BeforeEach
    void setup() {
        function = new Function(identifier);
        p1 = Mockito.mock(Expression.class);
        p2 = Mockito.mock(Expression.class);
        Mockito.when(p1.toString()).thenReturn("bla1");
        Mockito.when(p2.toString()).thenReturn("bla2");
    }

    /**
     * Checks that the identifier is returned correctly.
     */
    @Test
    void getIdentifierTest() {
        assertThat(function.getIdentifier()).isEqualTo(identifier);
    }

    /**
     * Checks that the parts are added and returned correctly.
     */
    @Test
    void addPartsTest() {
        assertThat(function.getArguments()).isEmpty();
        Expression part = Mockito.mock(Expression.class);
        assertThat(function.addArgument(part)).isSameAs(function);
        assertThat(function.getArguments()).hasSize(1);
        assertThat(function.getArguments()).containsExactly(part);
    }

    /**
     * Checks that the toString override is correct.
     */
    @Test
    void toStringTest() {
        assertThat(function.toString()).isEqualTo(identifier + "()");
        assertThat(function.addArgument(p1).toString()).isEqualTo(identifier + "(bla1)");
        assertThat(function.addArgument(p2).toString()).isEqualTo(identifier + "(bla1, bla2)");
    }

    /**
     * Checks that two equal objects have the same hashcode.
     */
    @Test
    void hashCodeTest() {
        Function other = new Function(identifier);
        assertThat(function.hashCode()).isEqualTo(other.hashCode());
    }

    /**
     * Checks that an object is considered equal to itself.
     */
    @Test
    void equalSameObject() {
        assertThat(function).isEqualTo(function);
    }

    /**
     * Checks that two equal objects are considered equal.
     */
    @Test
    void equalSameIdentifier() {
        assertThat(function).isEqualTo(new Function(identifier));
    }

    /**
     * Checks that two equal objects are considered equal.
     */
    @Test
    void equalSameParts() {
        assertThat(function.addArgument(p1)).isEqualTo(new Function(identifier).addArgument(p1));
    }

    /**
     * Checks that objects with different identifiers are not considered equal.
     */
    @Test
    void notEqualDifferentIdentifier() {
        assertThat(function).isNotEqualTo(new Function("WGD"));
    }

    /**
     * Checks that objects with different parts are not considered equal.
     */
    @Test
    void notEqualDifferentParts() {
        assertThat(function).isNotEqualTo(new Function(identifier).addArgument(p2));
        assertThat(function.addArgument(p1)).isNotEqualTo(new Function(identifier).addArgument(p2));
    }

    /**
     * Checks that objects with different types are not considered equal.
     */
    @Test
    void notEqualDifferentType() {
        assertThat(function).isNotEqualTo(new Constant(identifier));
    }

    /**
     * Checks that an object is not considered to be equal to null.
     */
    @Test
    void notEqualNull() {
        assertThat(function).isNotEqualTo(null);
    }
}
