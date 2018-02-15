package nl.tudelft.goalkeeper.parser.results.files.module.details;

import languageTools.program.agent.Module;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the ExitConditionType class.
 */
class ExitConditionTypeTest {

    /**
     * Checks that the names are returned correctly.
     */
    @Test
    void nameTest() {
        assertThat(ExitConditionType.ALWAYS.getName()).isEqualTo("always");
        assertThat(ExitConditionType.NEVER.getName()).isEqualTo("never");
        assertThat(ExitConditionType.NOGOALS.getName()).isEqualTo("nogoals");
        assertThat(ExitConditionType.NOACTION.getName()).isEqualTo("noaction");
    }

    /**
     * Checks that the toString is correctly returning the name.
     */
    @Test
    void toStringTest() {
        assertThat(ExitConditionType.ALWAYS.toString()).isEqualTo(ExitConditionType.ALWAYS.getName());
    }

    /**
     * Checks that the correct conditions get mapped to the correct types.
     */
    @Test
    void getTest() {
        assertThat(ExitConditionType.get(null)).isEqualTo(ExitConditionType.UNKNOWN);
        assertThat(ExitConditionType.get(Module.ExitCondition.ALWAYS)).isEqualTo(ExitConditionType.ALWAYS);
        assertThat(ExitConditionType.get(Module.ExitCondition.NEVER)).isEqualTo(ExitConditionType.NEVER);
        assertThat(ExitConditionType.get(Module.ExitCondition.NOGOALS)).isEqualTo(ExitConditionType.NOGOALS);
        assertThat(ExitConditionType.get(Module.ExitCondition.NOACTION)).isEqualTo(ExitConditionType.NOACTION);
    }
}
