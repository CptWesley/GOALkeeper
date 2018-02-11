package nl.tudelft.goalkeeper.parser.results.files.actionspec;

import nl.tudelft.goalkeeper.parser.results.parts.Expression;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the ActionSpecification class.
 */
class ActionSpecificationTest {

    private static final String NAME = "sduin q2i35-243";

    private ActionSpecification as;
    private Expression pre, post, param1, param2;

    /**
     * Sets up the testing environment before each test.
     */
    @BeforeEach
    void setup() {
        pre = Mockito.mock(Expression.class);
        post = Mockito.mock(Expression.class);
        param1 = Mockito.mock(Expression.class);
        param2 = Mockito.mock(Expression.class);
        as = new ActionSpecification(NAME, pre, post, Arrays.asList(param1, param2));
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

    /**
     * Checks that the parameters are passed correctly.
     */
    @Test
    void getParametersTest() {
        assertThat(as.getParameters()).containsExactly(param1, param2);
    }

    /**
     * Checks that the toString method works correctly.
     */
    @Test
    void toStringTest() {
        Mockito.when(pre.toString()).thenReturn("AAA");
        Mockito.when(post.toString()).thenReturn("BBB");
        Mockito.when(param1.toString()).thenReturn("CCC");
        Mockito.when(param2.toString()).thenReturn("DDD");
        assertThat(as.toString()).isEqualTo("define " + NAME + "(CCC, DDD) with pre {AAA} post {BBB}");
        as = new ActionSpecification(NAME, pre, post, new ArrayList<>());
        assertThat(as.toString()).isEqualTo("define " + NAME + " with pre {AAA} post {BBB}");
    }
}
