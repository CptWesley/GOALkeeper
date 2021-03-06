package nl.tudelft.goalkeeper.parser.results.files.actionspec.parsers;

import krTools.language.Term;
import languageTools.program.actionspec.UserSpecAction;
import nl.tudelft.goalkeeper.exceptions.InvalidKRLanguageException;
import nl.tudelft.goalkeeper.exceptions.UnknownKRLanguageException;
import nl.tudelft.goalkeeper.parser.queries.ExpressionParser;
import nl.tudelft.goalkeeper.parser.results.files.actionspec.ActionSpecification;
import nl.tudelft.goalkeeper.parser.results.parts.Expression;

import java.util.LinkedList;
import java.util.List;

/**
 * Class which parses action specifications.
 */
public final class ActionSpecificationParser {

    /**
     * Prevents instantiation.
     */
    private ActionSpecificationParser() { }

    /**
     * Parses a UserSpecAction instance to a GOALkeeper ActionSpecification.
     * @param action Action specification to convert.
     * @return A GOALkeeper version of action.
     */
    public static ActionSpecification parse(UserSpecAction action) {
        String name = action.getName();
        Expression pre = null;
        Expression post = null;
        List<Expression> parameters = new LinkedList<>();
        try {
            ExpressionParser expressionparser = new ExpressionParser();
            pre = expressionparser.parse(action.getFullPreCondition()
                    .getPreCondition());
            post = expressionparser.parse(action.getPositivePostcondition()
                    .getPostCondition().toQuery());
            for (Term t : action.getParameters()) {
                parameters.add(expressionparser.parse(t));
            }
        } catch (UnknownKRLanguageException | InvalidKRLanguageException e) {
            e.printStackTrace();
        }
        return new ActionSpecification(name, pre, post, parameters);
    }
}
