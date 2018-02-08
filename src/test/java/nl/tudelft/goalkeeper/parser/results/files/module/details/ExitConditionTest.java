package nl.tudelft.goalkeeper.parser.results.files.module.details;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the ExitCondition class.
 */
class ExitConditionTest extends ModuleDetailTest {

    private ExitCondition condition;

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
        condition = new ExitCondition(ExitConditionType.ALWAYS);
    }

    /**
     * Checks that the type is returned correctly.
     */
    @Test
    void getTypeTest() {
        assertThat(condition.getType()).isEqualTo(ExitConditionType.ALWAYS);
    }
}
