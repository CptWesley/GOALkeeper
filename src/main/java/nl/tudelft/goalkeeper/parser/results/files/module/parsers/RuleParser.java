package nl.tudelft.goalkeeper.parser.results.files.module.parsers;

import krTools.language.Term;
import languageTools.program.agent.actions.SendAction;
import languageTools.program.agent.actions.UserSpecCallAction;
import languageTools.program.agent.rules.ForallDoRule;
import languageTools.program.agent.rules.ListallDoRule;
import nl.tudelft.goalkeeper.exceptions.UnknownKRLanguageException;
import nl.tudelft.goalkeeper.parser.results.files.module.Rule;
import nl.tudelft.goalkeeper.parser.results.files.module.RuleType;
import nl.tudelft.goalkeeper.parser.results.files.module.actions.Action;
import nl.tudelft.goalkeeper.parser.results.files.module.conditions.Condition;
import nl.tudelft.goalkeeper.util.console.Console;
import nl.tudelft.goalkeeper.util.console.ConsoleColor;

/**
 * Class which parses a rule.
 */
public final class RuleParser {

    /**
     * Prevents instantiation.
     */
    private RuleParser() { }

    /**
     * Parses a rule.
     * @param r Rule to parse.
     * @return GOALkeeper rule version of the rule.
     */
    public static Rule parse(languageTools.program.agent.rules.Rule r) {
        Rule rule = new Rule(getType(r));
        for (languageTools.program.agent.msc.MentalLiteral l
                : r.getCondition().getAllLiterals()) {
            try {
                Condition c = ConditionParser.parse(l);
                rule.addCondition(c);
            } catch (UnknownKRLanguageException e) {
                e.printStackTrace();
            }
        }
        for (languageTools.program.agent.actions.Action<?> a : r.getAction().getActions()) {
            Console.setUseColor(true);
            Console.println(a.toString(), ConsoleColor.RED);
            Console.println(a.getParameters().size(), ConsoleColor.BLACK);
            Console.println(a.getClass().toString(), ConsoleColor.BLUE);
            Console.println(a.getSignature(), ConsoleColor.YELLOW);

            if (a instanceof UserSpecCallAction) {
                UserSpecCallAction spec = (UserSpecCallAction) a;
                Console.println(spec.getSpecification().getSourceInfo().getSource(), ConsoleColor.GREEN);
            }
            if (a instanceof SendAction) {
                SendAction spec = (SendAction) a;
                for (Term t : spec.getSelector().getParameters()) {
                    Console.println((t), ConsoleColor.GREEN);
                }
            }
            for (Object p : a.getParameters()) {
                Console.println(p.toString(), ConsoleColor.PURPLE);
            }

            try {
                Action action = ActionParser.parse(a);
                rule.addAction(action);
            } catch (UnknownKRLanguageException e) {
                e.printStackTrace();
            }
        }

        return rule;
    }

    /**
     * Gets the type of a rule.
     * @param r Rule to get the type from.
     * @return Type of the rule.
     */
    private static RuleType getType(languageTools.program.agent.rules.Rule r) {
        if (r instanceof ListallDoRule) {
            return RuleType.LISTALL;
        }
        if (r instanceof ForallDoRule) {
            return RuleType.FORALL;
        }
        return RuleType.IF;
    }
}
