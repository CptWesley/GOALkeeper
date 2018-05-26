package nl.tudelft.goalkeeper.parser.results.parts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the Compound class.
 */
class CompoundTest extends ExpressionTest {

    private static final String identifier = "DGFDS";

    private Compound compound;
    private Expression p1, p2;

    /**
     * {@inheritDoc}
     */
    @Override
    Expression getInstance() {
        return compound;
    }

    /**
     * Sets up the testing environment before each test.
     */
    @BeforeEach
    void setup() {
        compound = new Compound(identifier);
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
        assertThat(compound.getIdentifier()).isEqualTo(identifier);
    }

    /**
     * Checks that the parts are added and returned correctly.
     */
    @Test
    void addPartsTest() {
        assertThat(compound.getArguments()).isEmpty();
        Expression part = Mockito.mock(Expression.class);
        assertThat(compound.addArgument(part)).isSameAs(compound);
        assertThat(compound.getArguments()).hasSize(1);
        assertThat(compound.getArguments()).containsExactly(part);
    }

    /**
     * Checks that the toString override is correct.
     */
    @Test
    void toStringTest() {
        assertThat(compound.toString()).isEqualTo(identifier + "()");
        assertThat(compound.addArgument(p1).toString()).isEqualTo(identifier + "(bla1)");
        assertThat(compound.addArgument(p2).toString()).isEqualTo(identifier + "(bla1, bla2)");
    }

    /**
     * Checks that two equal objects have the same hashcode.
     */
    @Test
    void hashCodeTest() {
        Compound other = new Compound(identifier);
        assertThat(compound.hashCode()).isEqualTo(other.hashCode());
    }

    /**
     * Checks that an object is considered equal to itself.
     */
    @Test
    void equalSameObject() {
        assertThat(compound).isEqualTo(compound);
    }

    /**
     * Checks that two equal objects are considered equal.
     */
    @Test
    void equalSameIdentifier() {
        assertThat(compound).isEqualTo(new Compound(identifier));
    }

    /**
     * Checks that two equal objects are considered equal.
     */
    @Test
    void equalSameParts() {
        assertThat(compound.addArgument(p1)).isEqualTo(new Compound(identifier).addArgument(p1));
    }

    /**
     * Checks that objects with different identifiers are not considered equal.
     */
    @Test
    void notEqualDifferentIdentifier() {
        assertThat(compound).isNotEqualTo(new Compound("WGD"));
    }

    /**
     * Checks that objects with different parts are not considered equal.
     */
    @Test
    void notEqualDifferentParts() {
        assertThat(compound).isNotEqualTo(new Compound(identifier).addArgument(p2));
        assertThat(compound.addArgument(p1)).isNotEqualTo(new Compound(identifier).addArgument(p2));
    }

    /**
     * Checks that objects with different types are not considered equal.
     */
    @Test
    void notEqualDifferentType() {
        assertThat(compound).isNotEqualTo(new Constant(identifier));
    }

    /**
     * Checks that an object is not considered to be equal to null.
     */
    @Test
    void notEqualNull() {
        assertThat(compound).isNotEqualTo(null);
    }
}
