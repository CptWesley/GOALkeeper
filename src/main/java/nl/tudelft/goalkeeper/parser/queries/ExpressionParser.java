package nl.tudelft.goalkeeper.parser.queries;

import nl.tudelft.goalkeeper.exceptions.UnknownKRLanguageException;
import nl.tudelft.goalkeeper.parser.results.parts.Expression;
import swiprolog.language.PrologQuery;

/**
 * Class that parses expressions.
 */
public final class ExpressionParser {

    /**
     * Prevents instantiation.
     */
    private ExpressionParser() { }

    /**
     * Parses a GOAL query to a GOALkeeper expression.
     * @param expression Expression to parse.
     * @return Parsed expression.
     * @throws UnknownKRLanguageException Thrown when we can't handle the query.
     */
    public static Expression parse(krTools.language.Expression expression)
            throws UnknownKRLanguageException {
        return getParser(expression).parse(expression);
    }

    /**
     * Gets the an instance of the correct parser type.
     * @param expression Expression to get the type for.
     * @return Instance of the correct parser.
     * @throws UnknownKRLanguageException Thrown when we can't handle the query.
     */
    static ExpressionParserInterface getParser(krTools.language.Expression expression)
            throws UnknownKRLanguageException {
        if (expression instanceof PrologQuery) {
            return new PrologExpressionParser();
        }
        throw new UnknownKRLanguageException(
                String.format("Found query of type '%s'.", expression.getClass().getTypeName()));
    }
}
