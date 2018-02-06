package nl.tudelft.goalkeeper.parser.results.files.module.conditions;

import lombok.Getter;
import nl.tudelft.goalkeeper.parser.results.parts.Expression;

/**
 * Abstract class for conditions.
 */
public abstract class Condition {

    @Getter private Expression expression;

    /**
     * Gets the hash code modifier.
     * @return Hashcode modifier.
     */
    protected abstract int getHashModifier();

    /**
     * Gets the type string of the condition type.
     * @return The type string of the condition type.
     */
    protected abstract String getTypeName();

    /**
     * Creates a new Condition instance.
     * @param expression Expression of the condition.
     */
    protected Condition(Expression expression) {
        this.expression = expression;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (o != null && this.getClass().equals(o.getClass())) {
            Condition that = (Condition) o;
            return this.expression.equals(that.expression);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return expression.hashCode() ^ getHashModifier();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("%s(%s)", getTypeName(), expression);
    }
}
