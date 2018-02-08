package nl.tudelft.goalkeeper.parser.results.files.module.details;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the EvaluationOrder class.
 */
class EvaluationOrderTest extends ModuleDetailTest {

    private EvaluationOrder condition;

    /**
     * {@inheritDoc}
     */
    @Override
    ModuleDetail getInstance() {
        return condition;
    }

    /**
     * Sets up the testing environment before each test.
     */
    @BeforeEach
    void setup() {
        condition = new EvaluationOrder(EvaluationOrderType.LINEAR);
    }

    /**
     * Checks that the type is returned correctly.
     */
    @Test
    void getTypeTest() {
        assertThat(condition.getType()).isEqualTo(EvaluationOrderType.LINEAR);
    }

    /**
     * Checks that the .toString() method is implemented correctly.
     */
    @Test
    void toStringTest() {
        assertThat(condition.toString()).isEqualTo("order=linear.");
    }
}
