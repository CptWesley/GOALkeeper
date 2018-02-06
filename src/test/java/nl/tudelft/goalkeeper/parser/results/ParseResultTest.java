package nl.tudelft.goalkeeper.parser.results;

import nl.tudelft.goalkeeper.checking.violations.Violation;
import nl.tudelft.goalkeeper.parser.results.files.module.ModuleFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the ParseResult class.
 */
class ParseResultTest {

    private ParseResult result;

    /**
     * Sets up test environment before each test.
     */
    @BeforeEach
    void setup() {
        result = new ParseResult();
    }

    /**
     * Checks that setting the successful flag works.
     */
    @Test
    void successfulTest() {
        assertThat(result.isSuccessful()).isFalse();
        result.setSuccessful(true);
        assertThat(result.isSuccessful()).isTrue();
    }

    /**
     * Checks that violations are added successfully.
     */
    @Test
    void violationAdditionTest() {
        assertThat(result.getViolations()).isEmpty();
        Violation violation = Mockito.mock(Violation.class);
        assertThat(result.addViolation(violation)).isSameAs(result);
        assertThat(result.getViolations()).containsExactly(violation);
    }

    /**
     * Checks that modules are added successfully.
     */
    @Test
    void moduleAdditionTest() {
        assertThat(result.getModules()).isEmpty();
        ModuleFile module = Mockito.mock(ModuleFile.class);
        assertThat(result.addModule(module)).isSameAs(result);
        assertThat(result.getModules()).containsExactly(module);
    }
}
