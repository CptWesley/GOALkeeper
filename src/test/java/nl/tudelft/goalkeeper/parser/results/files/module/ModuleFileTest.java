package nl.tudelft.goalkeeper.parser.results.files.module;

import nl.tudelft.goalkeeper.parser.results.files.module.rules.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the ModuleFile class.
 */
class ModuleFileTest {

    private ModuleFile file;

    /**
     * Sets up the testing environment before each test.
     */
    @BeforeEach
    void setup() throws IOException {
        file = new ModuleFile("src/test/resources/testfiles/emptyfile.txt");
    }

    /**
     * Checks that rule adding works properly.
     */
    @Test
    void getRulesTest() {
        Rule rule = Mockito.mock(Rule.class);
        assertThat(file.getRules()).isEmpty();
        assertThat(file.addRule(rule)).isSameAs(file);
        assertThat(file.getRules()).hasSize(1);
        assertThat(file.getRules().get(0)).isSameAs(rule);
    }
}
