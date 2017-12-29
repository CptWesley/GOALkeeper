package nl.tudelft.goalkeeper.parser.results.files.module.conditions;

import nl.tudelft.goalkeeper.parser.results.parts.Expression;
import nl.tudelft.goalkeeper.parser.results.parts.MessageMood;
import nl.tudelft.goalkeeper.parser.results.parts.Parameter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the SentCondition class.
 */
class SentConditionTest extends ConditionTest {

    private SentCondition condition;
    private Parameter selector;

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
        return new SentCondition(selector, MessageMood.IMPERATIVE);
    }

    /**
     * {@inheritDoc}
     */
    @BeforeEach
    void setup() {
        selector = Mockito.mock(Parameter.class);
        Mockito.when(selector.toString()).thenReturn("ABBA");
        condition = (SentCondition) getNewCondition();
    }

    /**
     * Checks that the hashModifier is correctly implemented.
     */
    @Test
    void hashModifierTest() {
        assertThat(condition.getHashModifier()).isEqualTo(83);
    }

    /**
     * Checks that the typeName getter is correctly implemented.
     */
    @Test
    void typeNameTest() {
        assertThat(condition.getTypeName()).isEqualTo("sent");
    }

    /**
     * Checks that equal objects are equal.
     */
    @Test
    void equalsSameTest() {
        SentCondition other = new SentCondition(selector, MessageMood.IMPERATIVE);
        assertThat(condition).isEqualTo(other);
        assertThat(condition.hashCode()).isEqualTo(other.hashCode());
        Expression e = Mockito.mock(Expression.class);
        condition.addExpression(e);
        other.addExpression(e);
        assertThat(condition).isEqualTo(other);
        assertThat(condition.hashCode()).isEqualTo(other.hashCode());
    }

    /**
     * Checks that toString is implemented correctly.
     */
    @Test
    void toStringTest() {
        assertThat(condition.toString()).isEqualTo("(ABBA)!sent()");
        Expression e1 = Mockito.mock(Expression.class);
        Expression e2 = Mockito.mock(Expression.class);
        Mockito.when(e1.toString()).thenReturn("raf1");
        Mockito.when(e2.toString()).thenReturn("raf2");
        assertThat(condition.addExpression(e1).toString()).isEqualTo("(ABBA)!sent(raf1)");
        assertThat(condition.addExpression(e2).toString()).isEqualTo("(ABBA)!sent(raf1, raf2)");
    }

    /**
     * Checks that we can retrieve the mood correctly.
     */
    @Test
    void getMoodTest() {
        assertThat(condition.getMood()).isEqualTo(MessageMood.IMPERATIVE);
    }

    /**
     * Checks that we can retrieve the sender correctly.
     */
    @Test
    void getSelectorTest() {
        assertThat(condition.getSelector()).isEqualTo(selector);
    }
}
