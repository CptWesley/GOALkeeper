package nl.tudelft.goalkeeper.parser.results.parts;

import lombok.Getter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Class for holding queries and subqueries.
 */
public final class Query implements Expression {

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
}
