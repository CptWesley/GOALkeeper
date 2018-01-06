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
        return new SentCondition(Mockito.mock(Expression.class), selector, MessageMood.IMPERATIVE);
    }

    /**
     * {@inheritDoc}
     */
    @BeforeEach
    void setup() {
        selector = Mockito.mock(Parameter.class);
        Mockito.when(selector.toString()).thenReturn("ABBA");
        condition = (SentCondition) getNewCondition();
        expression = condition.getExpression();
        Mockito.when(expression.toString()).thenReturn("raf1");
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
        assertThat(condition.getTypeName()).isEqualTo("sent!");
    }

    /**
     * Checks that equal objects are equal.
     */
    @Test
    void equalsSameTest() {
        SentCondition other = new SentCondition(expression, selector, MessageMood.IMPERATIVE);
        assertThat(condition).isEqualTo(other);
        assertThat(condition.hashCode()).isEqualTo(other.hashCode());
    }

    /**
     * Checks that objects with different moods are not considered equal.
     */
    @Test
    void notEqualsDifferentMoodTest() {
        SentCondition other = new SentCondition(expression, selector, MessageMood.INDICATIVE);
        assertThat(condition).isNotEqualTo(other);
    }

    /**
     * Checks that objects with different senders are not considered equal.
     */
    @Test
    void notEqualsDifferentSenderTest() {
        SentCondition other = new SentCondition(expression, Mockito.mock(Expression.class), MessageMood.IMPERATIVE);
        assertThat(condition).isNotEqualTo(other);
    }

    /**
     * Checks that toString is implemented correctly.
     */
    @Test
    void toStringTest() {
        assertThat(condition.toString()).isEqualTo("(ABBA).sent!(raf1)");
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
    void getSenderTest() {
        assertThat(condition.getSender()).isEqualTo(selector);
    }
}
