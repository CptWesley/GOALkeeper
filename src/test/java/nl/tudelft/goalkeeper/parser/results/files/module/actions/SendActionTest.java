package nl.tudelft.goalkeeper.parser.results.files.module.actions;

import nl.tudelft.goalkeeper.parser.results.parts.Expression;
import nl.tudelft.goalkeeper.parser.results.parts.MessageMood;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the SendAction class.
 */
class SendActionTest {

    private SendAction action;
    private Expression r1, r2, msg;
    private MessageMood mood;

    /**
     * Sets up the testing environment before each test.
     */
    @BeforeEach
    void setup() {
        r1 = Mockito.mock(Expression.class);
        r2 = Mockito.mock(Expression.class);
        msg = Mockito.mock(Expression.class);
        mood = Mockito.mock(MessageMood.class);
        action = new SendAction(msg, Arrays.asList(r1, r2), mood);
    }

    /**
     * Checks that the correct identifier is returned.
     */
    @Test
    void getIdentifierTest() {
        assertThat(action.getIdentifier()).isEqualTo("send/1");
    }

    /**
     * Checks that the correct message is returned.
     */
    @Test
    void getMessageTest() {
        assertThat(action.getExpression()).isSameAs(msg);
    }

    /**
     * Checks that the correct mood is returned.
     */
    @Test
    void getMoodTest() {
        assertThat(action.getMood()).isSameAs(mood);
    }

    /**
     * Checks that correct list of recipients is returned.
     */
    @Test
    void getRecipientsTest() {
        assertThat(action.getRecipients()).containsExactlyInAnyOrder(r1, r2);
    }

    /**
     * Checks if the toString method functions correctly.
     */
    @Test
    void toStringTest() {
        Mockito.when(r1.toString()).thenReturn("a");
        Mockito.when(r2.toString()).thenReturn("b");
        Mockito.when(msg.toString()).thenReturn("c");
        Mockito.when(mood.getSymbol()).thenReturn('[');
        assertThat(action.toString()).isEqualTo("(a, b).send[(c)");
    }

    /**
     * Checks that an object is equal to itself.
     */
    @Test
    void equalsSelf() {
        assertThat(action).isEqualTo(action);
        assertThat(action.hashCode()).isEqualTo(action.hashCode());
    }

    /**
     * Checks that an object is equal to the same object.
     */
    @Test
    void equalsSame() {
        SendAction other = new SendAction(msg, Arrays.asList(r2, r1), mood);
        assertThat(action).isEqualTo(other);
        assertThat(action.hashCode()).isEqualTo(other.hashCode());
    }

    /**
     * Checks that an object is not equal to null.
     */
    @Test
    void notEqualsNull() {
        assertThat(action).isNotEqualTo(null);
    }

    /**
     * Checks that an object is not equal to an object of a different type.
     */
    @Test
    void notEqualsDifferentType() {
        assertThat(action).isNotEqualTo("");
    }

    /**
     * Checks that objects are not considered equal if they have different moods.
     */
    @Test
    void notEqualsDifferentMood() {
        SendAction other = new SendAction(msg, Arrays.asList(r2, r1), Mockito.mock(MessageMood.class));
        assertThat(action).isNotEqualTo(other);
    }

    /**
     * Checks that objects are not considered equal if they have different messages.
     */
    @Test
    void notEqualsDifferentMessage() {
        SendAction other = new SendAction(Mockito.mock(Expression.class), Arrays.asList(r2, r1), mood);
        assertThat(action).isNotEqualTo(other);
    }

    /**
     * Checks that objects are not considered equal if they have different recipients.
     */
    @Test
    void notEqualsDifferentRecipients() {
        SendAction other = new SendAction(msg, Arrays.asList(r2, Mockito.mock(Expression.class)), mood);
        assertThat(action).isNotEqualTo(other);
    }

    /**
     * Checks that objects are not considered equal if they have different recipient amount.
     */
    @Test
    void notEqualsDifferentRecipientSize() {
        SendAction other = new SendAction(msg, Arrays.asList(r2), mood);
        assertThat(action).isNotEqualTo(other);
    }

}
