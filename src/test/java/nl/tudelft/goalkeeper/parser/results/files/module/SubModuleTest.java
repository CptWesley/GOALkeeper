package nl.tudelft.goalkeeper.parser.results.files.module;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the SubModule class.
 */
class SubModuleTest extends ModuleTest {

    private static final String NEWLINE = System.getProperty("line.separator");
    private SubModule module;

    /**
     * {@inheritDoc}
     */
    @Override
    Module getModule() {
        return module;
    }

    /**
     * Sets up the testing environment before each test.
     */
    @BeforeEach
    void setup() {
        module = new SubModule();
    }

    /**
     * Checks that the toString method returns the correct string.
     */
    @Test
    void toStringTest() {
        Rule r1 = Mockito.mock(Rule.class);
        Rule r2 = Mockito.mock(Rule.class);
        Mockito.when(r1.toString()).thenReturn("a");
        Mockito.when(r2.toString()).thenReturn("b");
        module.addRule(r1);
        module.addRule(r2);
        assertThat(module.toString()).isEqualTo(NEWLINE + "a" + NEWLINE + "b");
    }
}
