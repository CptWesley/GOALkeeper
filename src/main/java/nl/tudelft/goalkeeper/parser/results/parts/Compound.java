package nl.tudelft.goalkeeper.parser.results.parts;

import lombok.Getter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Class for holding queries and subqueries.
 */
public final class Compound extends Expression {

    private static final int HASH_MODIFIER = 43;

    @Getter private String identifier;
    private List<Expression> arguments;

    /**
     * Creates a query instance.
     * @param identifier Identifier of the query.
     */
    public Compound(String identifier) {
        super();
        this.identifier = identifier;
        this.arguments = new LinkedList<>();
    }

    /**
     * Adds a part to the query.
     * @param argument Part of the query.
     * @return Current query.
     */
    public Compound addArgument(Expression argument) {
        arguments.add(argument);
        return this;
    }

    /**
     * Gets the parts of the query.
     * @return List containing all parts of the query.
     */
    public List<Expression> getArguments() {
        return Collections.unmodifiableList(arguments);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Compound) {
            Compound that = (Compound) o;
            if (!this.identifier.equals(that.identifier)) {
                return false;
            }
            if (that.arguments.size() != this.arguments.size()) {
                return false;
            }
            for (int i = 0; i < this.arguments.size(); ++i) {
                if (!that.arguments.get(i).equals(this.arguments.get(i))) {
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
        int result = getIdentifier().hashCode() ^ HASH_MODIFIER;
        for (int i = 0; i < arguments.size(); ++i) {
            result += arguments.get(i).hashCode() ^ (i + 1);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(identifier).append('(');
        for (int i = 0; i < arguments.size(); ++i) {
            sb.append(arguments.get(i));
            if (i < arguments.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(')');
        return sb.toString();
    }
}
