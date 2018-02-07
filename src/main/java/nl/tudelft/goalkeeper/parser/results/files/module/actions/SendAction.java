package nl.tudelft.goalkeeper.parser.results.files.module.actions;

import lombok.Getter;
import lombok.Setter;
import nl.tudelft.goalkeeper.checking.violations.source.Source;
import nl.tudelft.goalkeeper.parser.results.parts.Expression;
import nl.tudelft.goalkeeper.parser.results.parts.MessageMood;
import nl.tudelft.goalkeeper.parser.results.parts.Sourceable;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Class representing send/1 actions.
 */
public final class SendAction implements Action, Sourceable {
    private static final String IDENTIFIER = "send/1";
    private List<Expression> recipients;

    @Getter private MessageMood mood;
    @Getter private Expression expression;
    @Getter @Setter private Source source;

    /**
     * Creates a new send action instance.
     * @param recipients Recipients of the send action.
     * @param expression Message of the send action.
     * @param mood Mood of the send action.
     */
    public SendAction(Expression expression, Collection<Expression> recipients, MessageMood mood) {
        this.recipients = new LinkedList<>();
        this.recipients.addAll(recipients);
        this.expression = expression;
        this.mood = mood;
    }

    /**
     * Gets the recipients of the message.
     * @return The recipients of the message.
     */
    public List<Expression> getRecipients() {
        return Collections.unmodifiableList(recipients);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getIdentifier() {
        return IDENTIFIER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        for (int i = 0; i < recipients.size(); ++i) {
            sb.append(recipients.get(i));
            if (i < recipients.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(").send")
                .append(mood.getSymbol())
                .append('(')
                .append(expression)
                .append(')');
        return sb.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof SendAction) {
            SendAction that = (SendAction) o;
            if (!mood.equals(that.mood)
                    || !expression.equals(that.expression)
                    || recipients.size() != that.recipients.size()) {
                return false;
            }
            for (Expression recipient : recipients) {
                if (!that.recipients.contains(recipient)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = 1;
        for (Expression recipient : recipients) {
            result *= recipient.hashCode();
        }
        return result ^ (expression.hashCode() * mood.hashCode());
    }
}
