package nl.tudelft.goalkeeper.parser.results.files.module.parsers;

import krTools.language.Query;
import krTools.language.Term;
import languageTools.program.agent.actions.ExitModuleAction;
import languageTools.program.agent.actions.ModuleCallAction;
import languageTools.program.agent.msg.SentenceMood;
import nl.tudelft.goalkeeper.exceptions.UnknownKRLanguageException;
import nl.tudelft.goalkeeper.parser.queries.ExpressionParser;
import nl.tudelft.goalkeeper.parser.results.files.module.actions.Action;
import nl.tudelft.goalkeeper.parser.results.files.module.actions.ExitAction;
import nl.tudelft.goalkeeper.parser.results.files.module.actions.InternalAction;
import nl.tudelft.goalkeeper.parser.results.files.module.actions.InternalActionType;
import nl.tudelft.goalkeeper.parser.results.files.module.actions.SendAction;
import nl.tudelft.goalkeeper.parser.results.files.module.actions.StartTimerAction;
import nl.tudelft.goalkeeper.parser.results.files.module.actions.SubModuleAction;
import nl.tudelft.goalkeeper.parser.results.parts.Expression;
import nl.tudelft.goalkeeper.parser.results.parts.MessageMood;

import java.util.LinkedList;
import java.util.List;

/**
 * Class which parses actions.
 */
public final class ActionParser {

    private static final String SUB_MODULE_PREFIX = "null/";

    /**
     * Prevents instantiation.
     */
    private ActionParser() { }

    /**
     * Parses an action to the GOALkeeper form.
     * @param a Action to parse.
     * @return GOALkeeper action.
     * @throws UnknownKRLanguageException Thrown when KR language could not be determined.
     */
    public static Action parse(languageTools.program.agent.actions.Action a)
            throws UnknownKRLanguageException {

        if (a instanceof languageTools.program.agent.actions.SendAction) {
            return parseSendAction((languageTools.program.agent.actions.SendAction) a);
        }
        if (a instanceof ExitModuleAction) {
            return new ExitAction();
        }
        if (a instanceof languageTools.program.agent.actions.StartTimerAction) {
            return parseStartTimerAction((languageTools.program.agent.actions.StartTimerAction) a);
        }
        if (a instanceof ModuleCallAction) {
            ModuleCallAction mca = (ModuleCallAction) a;
            if (mca.getSignature().length() >= SUB_MODULE_PREFIX.length()
                    && mca.getSignature()
                    .substring(0, SUB_MODULE_PREFIX.length()).equals(SUB_MODULE_PREFIX)) {
                return parseSubModuleAction(mca);
            }
        }
        if (InternalActionType.get(a.getSignature()) == null) {
            return new ExitAction();
        }
        return new InternalAction(InternalActionType.get(a.getSignature()), getExpression(a));
    }

    /**
     * Gets the expression of the action if there is one.
     * @param a Action to get the expression from.
     * @return Expression of the action. Returns null if there is none.
     * @throws UnknownKRLanguageException Thrown when KR language could not be identified.
     */
    private static Expression getExpression(languageTools.program.agent.actions.Action a)
            throws UnknownKRLanguageException {
        if (a.getParameters().size() > 0) {
            return ExpressionParser.parse((krTools.language.Expression) a.getParameters().get(0));
        }
        return null;
    }

    /**
     * Parses a SendAction to a GOALkeeper SendAction.
     * @param a SendAction to parse.
     * @return GOALkeeper SendAction version of a.
     * @throws UnknownKRLanguageException Thrown when KR language could not be determined.
     */
    private static SendAction parseSendAction(languageTools.program.agent.actions.SendAction a)
            throws UnknownKRLanguageException {
        List<Expression> recipients = new LinkedList<>();
        for (Term recipient : a.getSelector().getParameters()) {
            recipients.add(ExpressionParser.parse(recipient));
        }
        MessageMood mood = MessageMood.INDICATIVE;
        if (a.getMood().equals(SentenceMood.IMPERATIVE)) {
            mood = MessageMood.IMPERATIVE;
        } else if (a.getMood().equals(SentenceMood.INTERROGATIVE)) {
            mood = MessageMood.INTERROGATIVE;
        }
        return new SendAction(getExpression(a), recipients, mood);
    }

    /**
     * Parses a StartTimerAction to a GOALkeeper StartTimerAction.
     * @param a StartTimerAction to parse.
     * @return GOALkeeper StartTimerAction version of a.
     * @throws UnknownKRLanguageException Thrown when KR language could not be determined.
     */
    private static StartTimerAction parseStartTimerAction(
            languageTools.program.agent.actions.StartTimerAction a)
            throws UnknownKRLanguageException {
        Expression interval = ExpressionParser.parse(a.getParameters().get(1));
        Expression duration = ExpressionParser.parse(a.getParameters().get(2));
        return new StartTimerAction(getExpression(a), interval, duration);
    }

    private static SubModuleAction parseSubModuleAction(ModuleCallAction a) {
        return new SubModuleAction(ModuleParser.parseToSubModule(a.getTarget()));
    }
}
