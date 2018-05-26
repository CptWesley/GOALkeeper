package nl.tudelft.goalkeeper.parser.results.files.module.conditions;

import nl.tudelft.goalkeeper.parser.results.parts.Compound;
import nl.tudelft.goalkeeper.parser.results.parts.Constant;
import nl.tudelft.goalkeeper.parser.results.parts.Expression;
import nl.tudelft.goalkeeper.parser.results.parts.Parameter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the AGoalCondition class.
 */
class AGoalConditionTest extends ConditionTest {

    private AGoalCondition condition;
    private Expression expression;

    /**
     * {@inheritDoc}
     */
    @Override
    Condition getCondition() {
        return condition;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    Condition getNewCondition() {
        return new AGoalCondition(Mockito.mock(Expression.class));
    }

    /**
     * {@inheritDoc}
     */
    @BeforeEach
    void setup() {
        expression = Mockito.mock(Expression.class);
        Mockito.when(expression.toString()).thenReturn("raf1");
        condition = new AGoalCondition(expression);
    }

    /**
     * Checks that the hashModifier is correctly implemented.
     */
    @Test
    void hashModifierTest() {
        assertThat(condition.getHashModifier()).isEqualTo(33);
    }

    /**
     * Checks that the typeName getter is correctly implemented.
     */
    @Test
    void typeNameTest() {
        assertThat(condition.getTypeName()).isEqualTo("a-goal");
    }

    /**
     * Checks that equal objects are equal.
     */
    @Test
    void equalsSameTest() {
        AGoalCondition other = new AGoalCondition(expression);
        assertThat(condition).isEqualTo(other);
        assertThat(condition.hashCode()).isEqualTo(other.hashCode());
    }

    /**
     * Checks that toString is implemented correctly.
     */
    @Test
    void toStringTest() {
        assertThat(condition.toString()).isEqualTo("a-goal(raf1)");
    }

    @Test
    void getExpressionVariableTest() {

        expression = Mockito.mock(Parameter.class);
        condition = new AGoalCondition(expression);

        assertThat(condition.getExpressionVariable()).contains((Parameter) expression);
        assertThat(condition.getExpressionVariable()).hasSize(1);
    }

    @Test
    void getExpressionVariableEmpty() {
        expression = new Compound("test");
        condition = new AGoalCondition(expression);
        List conditionExpressionVariable = condition.getExpressionVariable();
        assertThat(condition.getExpressionVariable()).hasSize(0);
    }

    @Test
    void getExpressionVariableFromFunction() {
        expression = new Compound("test");
        Constant constant = new Constant("2");
        ((Compound)expression).addArgument(constant);
        condition = new AGoalCondition(expression);

        assertThat(condition.getExpressionVariable()).contains(constant);
        assertThat(condition.getExpressionVariable()).hasSize(1);
    }

    @Test
    void getExpressionVariableFromFunction2() {
        expression = new Compound("test");
        Constant constant = new Constant("2");
        Constant constant1 = new Constant("2");
        ((Compound)expression).addArgument(constant);
        ((Compound) expression).addArgument(constant1);
        condition = new AGoalCondition(expression);

        assertThat(condition.getExpressionVariable()).contains(constant);
        assertThat(condition.getExpressionVariable()).contains(constant1);
        assertThat(condition.getExpressionVariable()).hasSize(2);
    }

}

