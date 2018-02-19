package nl.tudelft.goalkeeper.parser.results.files.actionspec;

import nl.tudelft.goalkeeper.parser.results.parts.Expression;
import nl.tudelft.goalkeeper.parser.results.parts.KRLanguage;
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
        resetInstance();
    }

    /**
     * Resets the instance.
     */
    private void resetInstance() {
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

    /**
     * Checks that an action with no kr language has unknown language.
     */
    @Test
    void noLanguageTest() {
        Mockito.when(pre.getKRLanguage()).thenReturn(KRLanguage.UNKNOWN);
        Mockito.when(post.getKRLanguage()).thenReturn(KRLanguage.UNKNOWN);
        Mockito.when(param1.getKRLanguage()).thenReturn(KRLanguage.UNKNOWN);
        Mockito.when(param2.getKRLanguage()).thenReturn(KRLanguage.UNKNOWN);
        resetInstance();
        assertThat(as.getKRLanguage()).isEqualTo(KRLanguage.UNKNOWN);
    }

    /**
     * Checks that the language is correctly set when the pre condition has a KRLanguage.
     */
    @Test
    void preLanguageTest() {
        Mockito.when(pre.getKRLanguage()).thenReturn(KRLanguage.PROLOG);
        Mockito.when(post.getKRLanguage()).thenReturn(KRLanguage.UNKNOWN);
        Mockito.when(param1.getKRLanguage()).thenReturn(KRLanguage.UNKNOWN);
        Mockito.when(param2.getKRLanguage()).thenReturn(KRLanguage.UNKNOWN);
        resetInstance();
        assertThat(as.getKRLanguage()).isEqualTo(KRLanguage.PROLOG);
    }

    /**
     * Checks that the language is correctly set when the post condition has a KRLanguage.
     */
    @Test
    void postLanguageTest() {
        Mockito.when(pre.getKRLanguage()).thenReturn(KRLanguage.UNKNOWN);
        Mockito.when(post.getKRLanguage()).thenReturn(KRLanguage.PROLOG);
        Mockito.when(param1.getKRLanguage()).thenReturn(KRLanguage.UNKNOWN);
        Mockito.when(param2.getKRLanguage()).thenReturn(KRLanguage.UNKNOWN);
        resetInstance();
        assertThat(as.getKRLanguage()).isEqualTo(KRLanguage.PROLOG);
    }

    /**
     * Checks that the language is correctly set when a parameter has a KRLanguage.
     */
    @Test
    void paramLanguageTest() {
        Mockito.when(pre.getKRLanguage()).thenReturn(KRLanguage.UNKNOWN);
        Mockito.when(post.getKRLanguage()).thenReturn(KRLanguage.UNKNOWN);
        Mockito.when(param1.getKRLanguage()).thenReturn(KRLanguage.UNKNOWN);
        Mockito.when(param2.getKRLanguage()).thenReturn(KRLanguage.OWL);
        resetInstance();
        assertThat(as.getKRLanguage()).isEqualTo(KRLanguage.OWL);
    }
}
