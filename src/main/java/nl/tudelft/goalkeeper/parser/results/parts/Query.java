package nl.tudelft.goalkeeper.parser.results.parts;

import lombok.Getter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Class for holding queries and subqueries.
 */
public final class Query implements Expression {

    private static final int HASH_MODIFIER = 43;

    @Getter private String identifier;
    private List<Expression> parts;

    /**
     * Creates a query instance.
     * @param identifier Identifier of the query.
     */
    public Query(String identifier) {
        this.identifier = identifier;
        this.parts = new LinkedList<>();
    }

    /**
     * Adds a part to the query.
     * @param part Part of the query.
     * @return Current query.
     */
    public Query addPart(Expression part) {
        parts.add(part);
        return this;
    }

    /**
     * Gets the parts of the query.
     * @return List containing all parts of the query.
     */
    public List<Expression> getParts() {
        return Collections.unmodifiableList(parts);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Query) {
            Query that = (Query) o;
            if (!this.identifier.equals(that.identifier)) {
                return false;
            }
            if (that.parts.size() != this.parts.size()) {
                return false;
            }
            for (int i = 0; i < this.parts.size(); ++i) {
                if (!that.parts.get(i).equals(this.parts.get(i))) {
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
        for (int i = 0; i < parts.size(); ++i) {
            result += parts.get(i).hashCode() ^ (i + 1);
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
        for (int i = 0; i < parts.size(); ++i) {
            sb.append(parts.get(i));
            if (i < parts.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(')');
        return sb.toString();
    }
}
