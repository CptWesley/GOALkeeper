package nl.tudelft.goalkeeper.parser.queries;

import jpl.Term;
import nl.tudelft.goalkeeper.parser.results.parts.Constant;
import nl.tudelft.goalkeeper.parser.results.parts.Expression;
import nl.tudelft.goalkeeper.parser.results.parts.Function;
import nl.tudelft.goalkeeper.parser.results.parts.Variable;
import swiprolog.language.PrologQuery;

/**
 * Class which parses Prolog queries to GOALkeeper queries.
 */
public final class PrologExpressionParser implements ExpressionParserInterface {

    /**
     * {@inheritDoc}
     */
    @Override
    public Expression parse(krTools.language.Query query) {
        Term term = ((PrologQuery) query).getTerm();
        return parseTerm(term);
    }

    /**
     * Parses a prolog term to an expression.
     * @param term Term to parse.
     * @return Parsed expression.
     */
    private static Expression parseTerm(Term term) {
        if (term.isVariable()) {
            return new Variable(term.name());
        }
        if (term.isAtom()) {
            return new Constant(term.name());
        }
        if (term.isFloat()) {
            return new Constant(term.floatValue() + "");
        }
        if (term.isInteger()) {
            return new Constant(term.intValue() + "");
        }
        Function result = new Function(term.name());
        for (Term part : term.args()) {
            result.addPart(parseTerm(part));
        }
        return result;
    }
}
