package nl.tudelft.goalkeeper.parser.queries;

import nl.tudelft.goalkeeper.parser.results.parts.Expression;

/**
 * Interface for query parsers.
 */
public interface ExpressionParserInterface {

    /**
     * Parses original query to GOALkeeper expression type.
     * @param query Original GOAL query.
     * @return GOALkeeper expression version of the GOAL query.
     */
    Expression parse(krTools.language.Query query);
}
