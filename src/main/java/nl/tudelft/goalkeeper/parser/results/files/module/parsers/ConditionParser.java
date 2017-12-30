package nl.tudelft.goalkeeper.parser.results.files.module.parsers;

import krTools.language.Term;
import krTools.language.Var;
import languageTools.program.agent.msc.MentalLiteral;
import languageTools.program.agent.selector.Selector;
import nl.tudelft.goalkeeper.parser.ExpressionParser;
import nl.tudelft.goalkeeper.parser.results.files.module.conditions.BeliefCondition;
import nl.tudelft.goalkeeper.parser.results.files.module.conditions.Condition;
import nl.tudelft.goalkeeper.parser.results.files.module.conditions.PerceptCondition;
import nl.tudelft.goalkeeper.parser.results.files.module.conditions.SentCondition;
import nl.tudelft.goalkeeper.parser.results.parts.Constant;
import nl.tudelft.goalkeeper.parser.results.parts.Expression;
import nl.tudelft.goalkeeper.parser.results.parts.MessageMood;
import nl.tudelft.goalkeeper.parser.results.parts.Parameter;
import nl.tudelft.goalkeeper.parser.results.parts.Variable;
import swiprolog.language.PrologQuery;

/**
 * Class which parses conditions.
 */
public final class ConditionParser {

    /**
     * Prevents instantiation.
     */
    private ConditionParser() { }

    /**
     * Parses a query to a condition.
     * @param query Query to parse.
     * @return Condition of our own type.
     */
    public static Condition parse(MentalLiteral query) {
        Condition c = getInstance(query.getOperator(), query.getSelector());
        Expression e = ExpressionParser.parse(((PrologQuery) query.getFormula()).getTerm());
        c.addExpression(e);
        return c;
    }

    /**
     * Creates a proper instance of a condition.
     * @param operator Operator of the query.
     * @param selector Selector of the query.
     * @return
     */
    private static Condition getInstance(String operator, Selector selector) {
        Parameter newSelector = null;
        if (selector.getParameters().size() > 0) {
            Term term = selector.getParameters().get(0);
            if (term instanceof Var) {
                newSelector = new Variable(term.getSignature());
            } else {
                newSelector = new Constant(term.getSignature());
            }
        }
        switch (operator) {
            case "percept":
                return new PerceptCondition();
            case "sent:":
                return new SentCondition(newSelector, MessageMood.INDICATIVE);
            case "sent!":
                return new SentCondition(newSelector, MessageMood.IMPERATIVE);
            case "sent:?":
                return new SentCondition(newSelector, MessageMood.INTERROGATIVE);
            default:
                return new BeliefCondition();
        }
    }
}
