package nl.tudelft.goalkeeper.parser;

import jpl.Term;
import nl.tudelft.goalkeeper.parser.results.parts.Constant;
import nl.tudelft.goalkeeper.parser.results.parts.Expression;
import nl.tudelft.goalkeeper.parser.results.parts.Query;
import nl.tudelft.goalkeeper.parser.results.parts.Variable;

/**
 * Class that parses expressions.
 */
public final class ExpressionParser {

    /**
     * Prevents instantiation.
     */
    private ExpressionParser() { }

    /**
     * Parses a prolog term to an expression.
     * @param term Term to parse.
     * @return Parsed expression.
     */
    public static Expression parse(Term term) {
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
        Query result = new Query(term.name());

        for (Term part : term.args()) {
            result.addPart(parse(part));
        }

        return result;
    }
}
