package nl.tudelft.goalkeeper.parser.results.files.actionspec;

import nl.tudelft.goalkeeper.parser.results.parts.Expression;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the ActionSpecification class.
 */
class ActionSpecificationTest {

    private static final String NAME = "sduin q2i35-243";

    private ActionSpecification as;
    private Expression pre, post;

    /**
     * Sets up the testing environment before each test.
     */
    @BeforeEach
    void setup() {
        pre = Mockito.mock(Expression.class);
        post = Mockito.mock(Expression.class);
        as = new ActionSpecification(NAME, pre, post);
    }

    /**
     * Checks that the name of the action specification is returned correctly.
     */
    @Test
    void getNameTest() {
        assertThat(as.getName()).isEqualTo(NAME);
    }

    /**
     * Checks that the pre-condition of the action specification is returned correctly.
     */
    @Test
    void getPreTest() {
        assertThat(as.getPre()).isEqualTo(pre);
    }

    /**
     * Checks that the post-condition of the action specification is returned correctly.
     */
    @Test
    void getPostTest() {
        assertThat(as.getPost()).isEqualTo(post);
    }
}
