package nl.tudelft.goalkeeper.parser.queries;

import krTools.language.Term;
import nl.tudelft.goalkeeper.exceptions.InvalidKRLanguageException;
import nl.tudelft.goalkeeper.parser.results.parts.Compound;
import nl.tudelft.goalkeeper.parser.results.parts.Constant;
import nl.tudelft.goalkeeper.parser.results.parts.Expression;
import nl.tudelft.goalkeeper.parser.results.parts.KRLanguage;
import nl.tudelft.goalkeeper.parser.results.parts.Variable;
import swiprolog.language.PrologCompound;
import swiprolog.language.PrologDBFormula;
import swiprolog.language.PrologQuery;
import swiprolog.language.PrologTerm;
import swiprolog.language.PrologUpdate;
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
    private Expression parseExpression(krTools.language.Expression expression)
            throws InvalidKRLanguageException {
        if (expression instanceof PrologVar) {
            return new Variable(expression.getSignature());
        }
        if (expression instanceof PrologQuery) {
            return parse(((PrologQuery) expression).getCompound());
        }
        if (expression instanceof PrologDBFormula) {
            return parse(((PrologDBFormula) expression).getCompound());
        }
        if (expression instanceof PrologUpdate) {
            return parse(((PrologUpdate) expression).getCompound());
        }
        if (expression instanceof PrologTerm) {
            return new Constant(expression.getSignature());
        }
        if (expression instanceof PrologCompound) {
            PrologCompound compound = (PrologCompound) expression;
            if (compound.getArity() == 0) {
                return new Constant(compound.getSignature());
            }
            Compound result = new Compound(compound.getSignature());
            for (Term t : compound) {
                result.addArgument(parse(t));
            }
            return result;
        }

        throw new InvalidKRLanguageException(
                "Can't parse instance of type '" + expression.getClass() + "'.");
    }
}
