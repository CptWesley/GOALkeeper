package nl.tudelft.goalkeeper.parser.queries;

import krTools.parser.SourceInfo;
import lombok.Getter;
import lombok.Setter;
import nl.tudelft.goalkeeper.checking.violations.source.SourceParser;
import nl.tudelft.goalkeeper.exceptions.InvalidKRLanguageException;
import nl.tudelft.goalkeeper.exceptions.UnknownKRLanguageException;
import nl.tudelft.goalkeeper.parser.results.parts.Expression;
import swiprolog.language.PrologExpression;

/**
 * Class that parses expressions.
 */
public final class ExpressionParser {

    @Getter @Setter private SourceParser sourceParser;

    /**
     * Creates a new ExpressionParser instance.
     */
    public ExpressionParser() {
        sourceParser = new SourceParser();
    }

    /**
     * Parses a GOAL query to a GOALkeeper expression.
     * @param expression Expression to parse.
     * @return Parsed expression.
     * @throws UnknownKRLanguageException Thrown when we can't handle the query.
     * @throws InvalidKRLanguageException Thrown when we can't handle the query.
     */
    public Expression parse(krTools.language.Expression expression)
            throws UnknownKRLanguageException, InvalidKRLanguageException {
        Expression result = getParser(expression).parse(expression);
        SourceInfo sourceInfo = expression.getSourceInfo();
        if (sourceInfo != null) {
            result.setSource(sourceParser.parse(sourceInfo));
        }
        return result;
    }

    /**
     * Gets the an instance of the correct parser type.
     * @param expression Expression to get the type for.
     * @return Instance of the correct parser.
     * @throws UnknownKRLanguageException Thrown when we can't handle the query.
     */
    static ExpressionParserInterface getParser(krTools.language.Expression expression)
            throws UnknownKRLanguageException {
        if (expression instanceof PrologExpression) {
            return new PrologExpressionParser();
        }
        throw new UnknownKRLanguageException(
                String.format("Found query of type '%s'.", expression.getClass().getTypeName()));
    }
}
