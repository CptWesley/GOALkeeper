package nl.tudelft.goalkeeper.parser.results.files.actionspec;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the ActionSpecFile class.
 */
class ActionSpecFileTest {

    private ActionSpecFile file;

    /**
     * Sets up the testing environment before each test.
     */
    @BeforeEach
    void setup() throws IOException {
        file = new ActionSpecFile("src/test/resources/testfiles/emptyfile.txt");
    }

    /**
     * Checks that the specification list works correctly.
     */
    @Test
    void specificationsListTest() {
        ActionSpecification a1 = Mockito.mock(ActionSpecification.class);
        ActionSpecification a2 = Mockito.mock(ActionSpecification.class);
        assertThat(file.getActionSpecifications()).isEmpty();
        file.addActionSpecification(a1);
        assertThat(file.getActionSpecifications()).hasSize(1);
        assertThat(file.getActionSpecifications()).containsExactly(a1);
        file.addActionSpecification(a2);
        assertThat(file.getActionSpecifications()).hasSize(2);
        assertThat(file.getActionSpecifications()).containsExactly(a1, a2);
    }
}
