package nl.tudelft.goalkeeper.parser.results.parts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the Query class.
 */
class QueryTest {

    private static final String identifier = "DGFDS";

    private Query query;
    private Expression p1, p2;

    /**
     * Sets up the testing environment before each test.
     */
    @BeforeEach
    void setup() {
        query = new Query(identifier);
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
        assertThat(query.getIdentifier()).isEqualTo(identifier);
    }

    /**
     * Checks that the parts are added and returned correctly.
     */
    @Test
    void addPartsTest() {
        assertThat(query.getParts()).isEmpty();
        Expression part = Mockito.mock(Expression.class);
        assertThat(query.addPart(part)).isSameAs(query);
        assertThat(query.getParts()).hasSize(1);
        assertThat(query.getParts()).containsExactly(part);
    }

    /**
     * Checks that the toString override is correct.
     */
    @Test
    void toStringTest() {
        assertThat(query.toString()).isEqualTo(identifier + "()");
        assertThat(query.addPart(p1).toString()).isEqualTo(identifier + "(bla1)");
        assertThat(query.addPart(p2).toString()).isEqualTo(identifier + "(bla1, bla2)");
    }

    /**
     * Checks that two equal objects have the same hashcode.
     */
    @Test
    void hashCodeTest() {
        Query other = new Query(identifier);
        assertThat(query.hashCode()).isEqualTo(other.hashCode());
    }

    /**
     * Checks that an object is considered equal to itself.
     */
    @Test
    void equalSameObject() {
        assertThat(query).isEqualTo(query);
    }

    /**
     * Checks that two equal objects are considered equal.
     */
    @Test
    void equalSameIdentifier() {
        assertThat(query).isEqualTo(new Query(identifier));
    }

    /**
     * Checks that two equal objects are considered equal.
     */
    @Test
    void equalSameParts() {
        assertThat(query.addPart(p1)).isEqualTo(new Query(identifier).addPart(p1));
    }

    /**
     * Checks that objects with different identifiers are not considered equal.
     */
    @Test
    void notEqualDifferentIdentifier() {
        assertThat(query).isNotEqualTo(new Query("WGD"));
    }

    /**
     * Checks that objects with different parts are not considered equal.
     */
    @Test
    void notEqualDifferentParts() {
        assertThat(query).isNotEqualTo(new Query(identifier).addPart(p2));
        assertThat(query.addPart(p1)).isNotEqualTo(new Query(identifier).addPart(p2));
    }

    /**
     * Checks that objects with different types are not considered equal.
     */
    @Test
    void notEqualDifferentType() {
        assertThat(query).isNotEqualTo(new Constant(identifier));
    }

    /**
     * Checks that an object is not considered to be equal to null.
     */
    @Test
    void notEqualNull() {
        assertThat(query).isNotEqualTo(null);
    }
}
