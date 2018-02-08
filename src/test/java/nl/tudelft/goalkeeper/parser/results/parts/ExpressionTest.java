package nl.tudelft.goalkeeper.parser.results.parts;

import nl.tudelft.goalkeeper.checking.violations.source.Source;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Abstract test class for subclasses of the Expression class.
 */
abstract class ExpressionTest {

    /**
     * Gets an instance of the Expression class under test.
     * @return Expression instance.
     */
    abstract Expression getInstance();

    /**
     * Checks that we can set the source correctly.
     */
    @Test
    void setSourceTest() {
        assertThat(getInstance().getSource()).isNull();
        Source source = Mockito.mock(Source.class);
        getInstance().setSource(source);
        assertThat(getInstance().getSource()).isSameAs(source);
    }

    /**
     * Checks that we can set and retrieve the KRLanguage correctly.
     */
    @Test
    void setKRLanguageTest() {
        assertThat(getInstance().getKRLanguage()).isEqualTo(KRLanguage.UNKNOWN);
        getInstance().setKRLanguage(KRLanguage.PROLOG);
        assertThat(getInstance().getKRLanguage()).isEqualTo(KRLanguage.PROLOG);
    }
}
