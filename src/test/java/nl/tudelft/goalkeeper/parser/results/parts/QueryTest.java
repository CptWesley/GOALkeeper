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

    /**
     * Sets up the testing environment before each test.
     */
    @BeforeEach
    void setup() {
        query = new Query(identifier);
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
}
