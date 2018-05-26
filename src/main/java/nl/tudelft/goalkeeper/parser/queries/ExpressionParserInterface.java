package nl.tudelft.goalkeeper.parser.queries;

import nl.tudelft.goalkeeper.exceptions.InvalidKRLanguageException;
import nl.tudelft.goalkeeper.parser.results.parts.Expression;

/**
 * Interface for query parsers.
 */
public interface ExpressionParserInterface {

    /**
     * Parses original query to GOALkeeper expression type.
     * @param expression Original GOAL expression.
     * @return GOALkeeper expression version of the GOAL query.
     * @throws InvalidKRLanguageException Thrown when KR language of an inner expression is invalid.
     */
    Expression parse(krTools.language.Expression expression) throws InvalidKRLanguageException;
}
