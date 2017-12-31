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
     * @param query Query to parse.
     * @return Parsed expression.
     * @throws UnknownKRLanguageException Thrown when we can't handle the query.
     */
    public static Expression parse(krTools.language.Query query)
            throws UnknownKRLanguageException {
        return getParser(query).parse(query);
    }

    /**
     * Gets the an instance of the correct parser type.
     * @param query Query to get the type for.
     * @return Instance of the correct parser.
     * @throws UnknownKRLanguageException Thrown when we can't handle the query.
     */
    static ExpressionParserInterface getParser(krTools.language.Query query)
            throws UnknownKRLanguageException {
        if (query instanceof PrologQuery) {
            return new PrologExpressionParser();
        }
        throw new UnknownKRLanguageException(
                String.format("Found query of type '%s'.", query.getClass().getTypeName()));
    }
}
