package nl.tudelft.goalkeeper.parser.results.files.module.details;

import languageTools.program.agent.Module;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the EvaluationOrderType class.
 */
class EvaluationOrderTypeTest {

    /**
     * Checks that the names are returned correctly.
     */
    @Test
    void nameTest() {
        assertThat(EvaluationOrderType.LINEAR.getName()).isEqualTo("linear");
        assertThat(EvaluationOrderType.LINEARALL.getName()).isEqualTo("linearall");
        assertThat(EvaluationOrderType.LINEARRANDOM.getName()).isEqualTo("linearrandom");
        assertThat(EvaluationOrderType.LINEARALLRANDOM.getName()).isEqualTo("linearallrandom");
        assertThat(EvaluationOrderType.RANDOM.getName()).isEqualTo("random");
        assertThat(EvaluationOrderType.RANDOMALL.getName()).isEqualTo("randomall");
    }

    /**
     * Checks that the toString is correctly returning the name.
     */
    @Test
    void toStringTest() {
        assertThat(EvaluationOrderType.LINEARALLRANDOM.toString())
                .isEqualTo(EvaluationOrderType.LINEARALLRANDOM.getName());
    }

    /**
     * Checks that the correct orders get mapped to the correct types.
     */
    @Test
    void getTest() {
        assertThat(EvaluationOrderType.get(Module.RuleEvaluationOrder.LINEAR))
                .isEqualTo(EvaluationOrderType.LINEAR);
        assertThat(EvaluationOrderType.get(Module.RuleEvaluationOrder.LINEARALL))
                .isEqualTo(EvaluationOrderType.LINEARALL);
        assertThat(EvaluationOrderType.get(Module.RuleEvaluationOrder.LINEARRANDOM))
                .isEqualTo(EvaluationOrderType.LINEARRANDOM);
        assertThat(EvaluationOrderType.get(Module.RuleEvaluationOrder.LINEARALLRANDOM))
                .isEqualTo(EvaluationOrderType.LINEARALLRANDOM);
        assertThat(EvaluationOrderType.get(Module.RuleEvaluationOrder.RANDOM))
                .isEqualTo(EvaluationOrderType.RANDOM);
        assertThat(EvaluationOrderType.get(Module.RuleEvaluationOrder.RANDOMALL))
                .isEqualTo(EvaluationOrderType.RANDOMALL);
    }
}
