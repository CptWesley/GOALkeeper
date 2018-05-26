package nl.tudelft.goalkeeper.parser.queries;

import krTools.language.Term;
import nl.tudelft.goalkeeper.exceptions.InvalidKRLanguageException;
import nl.tudelft.goalkeeper.parser.results.parts.Compound;
import nl.tudelft.goalkeeper.parser.results.parts.Constant;
import nl.tudelft.goalkeeper.parser.results.parts.Expression;
import nl.tudelft.goalkeeper.parser.results.parts.KRLanguage;
import nl.tudelft.goalkeeper.parser.results.parts.Variable;
import swiprolog.language.PrologCompound;
import swiprolog.language.PrologQuery;
import swiprolog.language.PrologVar;

/**
 * Class which parses Prolog queries to GOALkeeper queries.
 */
public final class PrologExpressionParser implements ExpressionParserInterface {

    /**
     * {@inheritDoc}
     */
    @Override
    public Expression parse(krTools.language.Expression expression)
            throws InvalidKRLanguageException {
        Expression result = parseExpression(expression);
        result.setKRLanguage(KRLanguage.PROLOG);
        return result;
    }

    /**
     * Parses an expression.
     * @param expression Expression to parse.
     * @return GOALKeeper variant of the expression.
     * @throws InvalidKRLanguageException Thrown when the expression is of unknown format.
     */
    private Expression parseExpression(krTools.language.Expression expression) throws InvalidKRLanguageException {
        if (expression instanceof PrologVar) {
            PrologVar var = (PrologVar) expression;
            if (var.isClosed()) {
                return new Constant(var.getSignature());
            } else {
                return new Variable(var.getSignature());
            }
        }
        if (expression instanceof PrologCompound) {
            PrologCompound compound = (PrologCompound) expression;
            Compound result = new Compound(compound.getSignature());
            for (Term t : compound) {
                result.addArgument(parse(t));
            }
            return result;
        }
        if (expression instanceof PrologQuery) {
            PrologQuery query = (PrologQuery) expression;
            return parse(query.getCompound());
        }

        throw new InvalidKRLanguageException(
                "Can't parse instance of type '" + expression.getClass() + "'.");
    }
}
