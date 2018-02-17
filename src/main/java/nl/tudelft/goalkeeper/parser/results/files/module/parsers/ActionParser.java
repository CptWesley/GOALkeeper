package nl.tudelft.goalkeeper.parser.results.files.module.parsers;

import krTools.language.Term;
import krTools.parser.SourceInfo;
import languageTools.program.agent.actions.ExitModuleAction;
import languageTools.program.agent.actions.ModuleCallAction;
import languageTools.program.agent.actions.UserSpecCallAction;
import languageTools.program.agent.msg.SentenceMood;
import lombok.Getter;
import lombok.Setter;
import nl.tudelft.goalkeeper.checking.violations.source.SourceParser;
import nl.tudelft.goalkeeper.exceptions.UnknownKRLanguageException;
import nl.tudelft.goalkeeper.parser.queries.ExpressionParser;
import nl.tudelft.goalkeeper.parser.results.files.module.actions.Action;
import nl.tudelft.goalkeeper.parser.results.files.module.actions.ExitAction;
import nl.tudelft.goalkeeper.parser.results.files.module.actions.ExternalAction;
import nl.tudelft.goalkeeper.parser.results.files.module.actions.InternalAction;
import nl.tudelft.goalkeeper.parser.results.files.module.actions.InternalActionType;
import nl.tudelft.goalkeeper.parser.results.files.module.actions.ModuleAction;
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

    @Getter @Setter private ExpressionParser expressionParser;
    @Getter @Setter private SourceParser sourceParser;

    /**
     * Creates a new ActionParser instance.
     */
    public ActionParser() {
        expressionParser = new ExpressionParser();
        sourceParser = new SourceParser();
    }

    /**
     * Parses an action to the GOALkeeper form.
     * @param a Action to parse.
     * @return GOALkeeper action.
     * @throws UnknownKRLanguageException Thrown when KR language could not be determined.
     */
    public Action parse(languageTools.program.agent.actions.Action a)
            throws UnknownKRLanguageException {
        Action action = getInstance(a);
        SourceInfo sourceInfo = a.getSourceInfo();
        if (sourceInfo != null) {
            action.setSource(sourceParser.parse(sourceInfo));
        }
        return action;
    }

    /**
     * Parses an action to the a correct GOALkeeper instance.
     * @param a Action to parse.
     * @return GOALkeeper action.
     * @throws UnknownKRLanguageException Thrown when KR language could not be determined.
     */
    private Action getInstance(languageTools.program.agent.actions.Action a)
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
            return parseModuleAction(mca);
        }
        if (a instanceof UserSpecCallAction) {
            return parseExternalAction((UserSpecCallAction) a);
        }
        if (a.getSignature().equals("starttimer/1")) { //TODO: Remove me when GOAL fixes this.
            return new InternalAction(InternalActionType.CANCEL_TIMER, getExpression(a));
        }
        return new InternalAction(InternalActionType.get(a.getSignature()), getExpression(a));
    }

    /**
     * Gets the expression of the action if there is one.
     * @param a Action to get the expression from.
     * @return Expression of the action. Returns null if there is none.
     * @throws UnknownKRLanguageException Thrown when KR language could not be identified.
     */
    private Expression getExpression(languageTools.program.agent.actions.Action a)
            throws UnknownKRLanguageException {
        if (a.getParameters().size() > 0) {
            return expressionParser.parse((krTools.language.Expression) a.getParameters().get(0));
        }
        return null;
    }

    /**
     * Parses a SendAction to a GOALkeeper SendAction.
     * @param a SendAction to parse.
     * @return GOALkeeper SendAction version of a.
     * @throws UnknownKRLanguageException Thrown when KR language could not be determined.
     */
    private SendAction parseSendAction(languageTools.program.agent.actions.SendAction a)
            throws UnknownKRLanguageException {
        List<Expression> recipients = new LinkedList<>();
        for (Term recipient : a.getSelector().getParameters()) {
            recipients.add(expressionParser.parse(recipient));
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
    private StartTimerAction parseStartTimerAction(
            languageTools.program.agent.actions.StartTimerAction a)
            throws UnknownKRLanguageException {
        Expression interval = expressionParser.parse(a.getParameters().get(1));
        Expression duration = expressionParser.parse(a.getParameters().get(2));
        return new StartTimerAction(getExpression(a), interval, duration);
    }

    /**
     * Parses a ModuleCallAction calling a submodule to a GOALkeeper SubModuleAction.
     * @param a ModuleCallAction to parse.
     * @return GOALkeeper SubModuleAction version of a.
     */
    private SubModuleAction parseSubModuleAction(ModuleCallAction a) {
        return new SubModuleAction(new ModuleParser().parseToSubModule(a.getTarget()));
    }

    /**
     * Parses a ModuleCallAction calling a submodule to a GOALkeeper ModuleAction.
     * @param a ModuleCallAction to parse.
     * @return GOALkeeper ModuleAction version of a.
     * @throws UnknownKRLanguageException Thrown when KR language could not be determined.
     */
    private ModuleAction parseModuleAction(ModuleCallAction a)
            throws UnknownKRLanguageException {
        List<Expression> arguments = new LinkedList<>();
        for (Term t : a.getParameters()) {
            arguments.add(expressionParser.parse(t));
        }
        String source = a.getTarget().getSourceInfo().getSource();
        return new ModuleAction(source, arguments);
    }

    /**
     * Parses a UserSpecCallAction calling a submodule to a GOALkeeper ExternalAction.
     * @param a UserSpecCallAction to parse.
     * @return GOALkeeper ExternalAction version of a.
     * @throws UnknownKRLanguageException Thrown when KR language could not be determined.
     */
    private ExternalAction parseExternalAction(UserSpecCallAction a)
            throws UnknownKRLanguageException {
        List<Expression> arguments = new LinkedList<>();
        for (Term t : a.getParameters()) {
            arguments.add(expressionParser.parse(t));
        }
        String source = a.getSpecification().getSourceInfo().getSource();
        return new ExternalAction(source, arguments);
    }
}
