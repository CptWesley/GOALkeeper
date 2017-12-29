package nl.tudelft.goalkeeper.parser.results.files.module.conditions;

import nl.tudelft.goalkeeper.parser.results.parts.Expression;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Interface for conditions.
 */
public abstract class Condition {

    private List<Expression> expressions;

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
     */
    protected Condition() {
        this.expressions = new LinkedList<>();
    }

    /**
     * Gets the expressions of the condition.
     * @return List of expressions.
     */
    public List<Expression> getExpressions() {
        return Collections.unmodifiableList(expressions);
    }

    /**
     * Adds expression to the condition.
     * @return current Condition.
     */
    public Condition addExpression(Expression expression) {
        expressions.add(expression);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (o != null && this.getClass() == o.getClass()) {
            Condition that = (Condition) o;
            if (this.expressions.size() != that.expressions.size()) {
                return false;
            }
            for (int i = 0; i < expressions.size(); ++i) {
                if (!this.expressions.get(i).equals(that.expressions.get(i))) {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = 0;
        for (int i = 0; i < expressions.size(); ++i) {
            result += expressions.hashCode() * (1 + i) ^ getHashModifier();
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getTypeName()).append('(');
        for (int i = 0; i < expressions.size(); ++i) {
            sb.append(expressions.get(i));
            if (i < expressions.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(')');
        return sb.toString();
    }
}
