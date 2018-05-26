package nl.tudelft.goalkeeper.parser.results.files.module.parsers;

import krTools.language.Term;
import krTools.language.Var;
import krTools.parser.SourceInfo;
import languageTools.program.agent.msc.MentalLiteral;
import languageTools.program.agent.selector.Selector;
import lombok.Getter;
import lombok.Setter;
import nl.tudelft.goalkeeper.checking.violations.source.SourceParser;
import nl.tudelft.goalkeeper.exceptions.InvalidKRLanguageException;
import nl.tudelft.goalkeeper.exceptions.UnknownKRLanguageException;
import nl.tudelft.goalkeeper.parser.queries.ExpressionParser;
import nl.tudelft.goalkeeper.parser.results.files.module.conditions.AGoalCondition;
import nl.tudelft.goalkeeper.parser.results.files.module.conditions.BeliefCondition;
import nl.tudelft.goalkeeper.parser.results.files.module.conditions.Condition;
import nl.tudelft.goalkeeper.parser.results.files.module.conditions.GoalACondition;
import nl.tudelft.goalkeeper.parser.results.files.module.conditions.GoalCondition;
import nl.tudelft.goalkeeper.parser.results.files.module.conditions.PerceptCondition;
import nl.tudelft.goalkeeper.parser.results.files.module.conditions.SentCondition;
import nl.tudelft.goalkeeper.parser.results.parts.Constant;
import nl.tudelft.goalkeeper.parser.results.parts.Expression;
import nl.tudelft.goalkeeper.parser.results.parts.MessageMood;
import nl.tudelft.goalkeeper.parser.results.parts.Parameter;
import nl.tudelft.goalkeeper.parser.results.parts.Variable;

/**
 * Class which parses conditions.
 */
public final class ConditionParser {

    @Getter @Setter private ExpressionParser expressionParser;
    @Getter @Setter private SourceParser sourceParser;

    /**
     * Creates a new ConditionParser instance.
     */
    public ConditionParser() {
        expressionParser = new ExpressionParser();
        sourceParser = new SourceParser();
    }

    /**
     * Parses a query to a condition.
     * @param query Compound to parse.
     * @return Condition of our own type.
     * @throws UnknownKRLanguageException Thrown when we don't know what language was given.
     * @throws InvalidKRLanguageException Thrown when the expression is malformed.
     */
    public Condition parse(MentalLiteral query)
            throws UnknownKRLanguageException, InvalidKRLanguageException {
        Expression e = expressionParser.parse(query.getFormula());
        Condition condition = getInstance(query.getOperator(), query.getSelector(), e);
        SourceInfo sourceInfo = query.getSourceInfo();
        if (sourceInfo != null) {
            condition.setSource(sourceParser.parse(sourceInfo));
        }
        condition.setKRLanguage(e.getKRLanguage());
        return condition;
    }

    /**
     * Creates a proper instance of a condition.
     * @param operator Operator of the query.
     * @param selector Selector of the query.
     * @param expression Expression of the condition.
     * @return New condition instance of correct type and with correct expression.
     */
    @SuppressWarnings("PMD")
    private static Condition getInstance(String operator,
            Selector selector, Expression expression) {
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
                return new PerceptCondition(expression);
            case "goal":
                return new GoalCondition(expression);
            case "a-goal":
                return new AGoalCondition(expression);
            case "goal-a":
                return new GoalACondition(expression);
            case "sent:":
                return new SentCondition(expression, newSelector, MessageMood.INDICATIVE);
            case "sent!":
                return new SentCondition(expression, newSelector, MessageMood.IMPERATIVE);
            case "sent?":
                return new SentCondition(expression, newSelector, MessageMood.INTERROGATIVE);
            default:
                return new BeliefCondition(expression);
        }
    }
}
