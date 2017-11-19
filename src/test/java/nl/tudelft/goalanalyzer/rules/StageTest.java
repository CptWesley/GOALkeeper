package nl.tudelft.goalanalyzer.rules;

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
        assertThat(stage.getMin()).isEqualTo(Double.MIN_VALUE);
        assertThat(stage.getMax()).isEqualTo(Double.MAX_VALUE);
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
    void setMinTest() {
        assertThat(stage.setMin(3)).isSameAs(stage);
        assertThat(stage.getMin()).isEqualTo(3);
    }

    /**
     * Checks that maximum setting is properly handled.
     */
    @Test
    void setMaxTest() {
        assertThat(stage.setMax(6)).isSameAs(stage);
        assertThat(stage.getMax()).isEqualTo(6);
    }
}
