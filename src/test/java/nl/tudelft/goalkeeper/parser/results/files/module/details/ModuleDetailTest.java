package nl.tudelft.goalkeeper.parser.results.files.module.details;

import nl.tudelft.goalkeeper.checking.violations.source.Source;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Abstract test class for subclasses of the ModuleDetail class.
 */
abstract class ModuleDetailTest {

    /**
     * Gets the instance of a ModuleDetail under test.
     * @return Instance of a ModuleDetail under test.
     */
    abstract ModuleDetail getInstance();

    /**
     * Checks that we can set and get the source correctly.
     */
    @Test
    void getSetSourceTest() {
        assertThat(getInstance().getSource()).isNull();
        Source source = Mockito.mock(Source.class);
        getInstance().setSource(source);
        assertThat(getInstance().getSource()).isSameAs(source);
    }

    /**
     * Checks that the hasSource() method returns false when there is no source.
     */
    @Test
    void hasSourceFalseTest() {
        assertThat(getInstance().hasSource()).isFalse();
    }

    /**
     * Checks that the hasSource() method returns true when there is a source.
     */
    @Test
    void hasSourceTrueTest() {
        Source source = Mockito.mock(Source.class);
        getInstance().setSource(source);
        assertThat(getInstance().hasSource()).isTrue();
    }
}
