package nl.tudelft.goalkeeper.parser.queries;

import jpl.Term;
import nl.tudelft.goalkeeper.parser.results.parts.Constant;
import nl.tudelft.goalkeeper.parser.results.parts.Query;
import nl.tudelft.goalkeeper.parser.results.parts.Variable;
import swiprolog.language.PrologQuery;

/**
 * Class that parses expressions.
 */
public final class QueryParser {

    /**
     * Prevents instantiation.
     */
    private QueryParser() { }

    /**
     * Parses a prolog term to a query.
     * @param term Term to parse.
     * @return Parsed expression.
     */
    public static Query parse(krTools.language.Query query) {
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

    private static QueryParserInterface getParser(krTools.language.Query query) {
        if (query instanceof PrologQuery) {
            return new PrologQueryParser();
        }
    }
}
