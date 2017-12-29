package nl.tudelft.goalkeeper.parser.results.files.module.conditions;

import lombok.Getter;
import nl.tudelft.goalkeeper.parser.results.parts.MessageMood;
import nl.tudelft.goalkeeper.parser.results.parts.Parameter;

/**
 * Class for all sent conditions.
 */
public final class SentCondition extends Condition {

    private static final int HASH_MODIFIER = 83;
    private static final String TYPE_NAME = "sent";

    @Getter private MessageMood mood;
    @Getter private Parameter selector;

    /**
     * Creates a new sent condition condition.
     * @param selector Selector of the condition.
     * @param mood Mood of the message.
     */
    public SentCondition(Parameter selector, MessageMood mood) {
        this.selector = selector;
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
        return TYPE_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (super.equals(o)) {
            SentCondition that = (SentCondition) o;
            return mood.equals(that.mood) && selector.equals(that.selector);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return super.hashCode() ^ (mood.hashCode() * selector.hashCode());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append('(')
                .append(selector)
                .append(')')
                .append(mood)
                .append(super.toString());
        return sb.toString();
    }
}
