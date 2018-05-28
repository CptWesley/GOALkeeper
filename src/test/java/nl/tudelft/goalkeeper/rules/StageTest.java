package nl.tudelft.goalkeeper.rules;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the Stage class.
 */
class StageTest {

    private Stage stage;

    /**
     * Prepares the testing environment before each test.
     */
    @BeforeEach
    void setup() {
        stage = new Stage();
    }

    /**
     * Checks that the default values are correct.
     */
    @Test
    void defaultTest() {
        assertThat(stage.getLimit()).isEqualTo(0);
        assertThat(stage.getSeverity()).isEqualTo(0);
    }

    /**
     * Checks that severity setting is properly handled.
     */
    @Test
    void setSeverityTest() {
        assertThat(stage.setSeverity(2)).isSameAs(stage);
        assertThat(stage.getSeverity()).isEqualTo(2);
    }

    /**
     * Checks that minimum setting is properly handled.
     */
    @Test
    void setLimitTest() {
        assertThat(stage.setLimit(3)).isSameAs(stage);
        assertThat(stage.getLimit()).isEqualTo(3);
    }
}
