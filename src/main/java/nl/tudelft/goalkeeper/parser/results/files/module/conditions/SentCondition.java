package nl.tudelft.goalkeeper.parser.results.files.module.conditions;

import lombok.Getter;
import nl.tudelft.goalkeeper.parser.results.parts.Expression;
import nl.tudelft.goalkeeper.parser.results.parts.MessageMood;

/**
 * Class for all sent conditions.
 */
public final class SentCondition extends Condition {

    private static final int HASH_MODIFIER = 83;
    private static final String TYPE_NAME = "sent";

    @Getter private MessageMood mood;
    @Getter private Expression sender;

    /**
     * Creates a new sent condition condition.
     * @param sender Sender of the message.
     * @param mood Mood of the message.
     * @param expression Expression of the condition.
     */
    public SentCondition(Expression expression, Expression sender, MessageMood mood) {
        super(expression);
        this.sender = sender;
        this.mood = mood;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getHashModifier() {
        return HASH_MODIFIER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getTypeName() {
        return TYPE_NAME + mood.getSymbol();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (super.equals(o)) {
            SentCondition that = (SentCondition) o;
            return mood.equals(that.mood) && sender.equals(that.sender);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return super.hashCode() ^ (mood.hashCode() * sender.hashCode());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append('(')
                .append(sender)
                .append(").")
                .append(super.toString());
        return sb.toString();
    }
}
