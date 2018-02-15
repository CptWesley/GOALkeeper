package nl.tudelft.goalkeeper.parser.results.parts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class of the Constant class.
 */
class ConstantTest extends ExpressionTest {
    private static final String n1 = "Name";

    private Constant var;

    /**
     * {@inheritDoc}
     */
    @Override
    Expression getInstance() {
        return var;
    }

    /**
     * Sets up the environment before each test.
     */
    @BeforeEach
    void setup() {
        var = new Constant(n1);
    }

    /**
     * Checks that the identifier getter works.
     */
    @Test
    void getIdentifierTest() {
        assertThat(var.getIdentifier()).isEqualTo(n1);
    }

    /**
     * Checks that the toString override is correct.
     */
    @Test
    void toStringTest() {
        assertThat(var.toString()).isEqualTo(n1);
    }

    /**
     * Checks that two equal objects have the same hashcode.
     */
    @Test
    void hashCodeTest() {
        Constant other = new Constant(n1);
        assertThat(var.hashCode()).isEqualTo(other.hashCode());
    }

    /**
     * Checks that an object is considered equal to itself.
     */
    @Test
    void equalSameObject() {
        assertThat(var).isEqualTo(var);
    }

    /**
     * Checks that two equal objects are considered equal.
     */
    @Test
    void equalSameIdentifier() {
        assertThat(var).isEqualTo(new Constant(n1));
    }

    /**
     * Checks that objects with different identifiers are not considered equal.
     */
    @Test
    void notEqualDifferentIdentifier() {
        assertThat(var).isNotEqualTo(new Constant("WGD"));
    }

    /**
     * Checks that objects with different types are not considered equal.
     */
    @Test
    void notEqualDifferentType() {
        assertThat(var).isNotEqualTo(5);
    }

    /**
     * Checks that an object is not considered to be equal to null.
     */
    @Test
    void notEqualNull() {
        assertThat(var).isNotEqualTo(null);
    }
}
