package nl.tudelft.goalkeeper.parser.results.files.module;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Abstract test class for Module subclasses.
 */
abstract class ModuleTest {

    /**
     * Gets the module instance for the current test.
     * @return Module instance for the current test.
     */
    abstract Module getModule();

    /**
     * Checks that adding rules properly work.
     */
    @Test
    void addRuleTest() {
        Rule r1 = Mockito.mock(Rule.class);
        Rule r2 = Mockito.mock(Rule.class);
        assertThat(getModule().getRules()).isEmpty();
        getModule().addRule(r1);
        assertThat(getModule().getRules()).containsExactly(r1);
        getModule().addRule(r2);
        assertThat(getModule().getRules()).containsExactly(r1, r2);
    }
}
