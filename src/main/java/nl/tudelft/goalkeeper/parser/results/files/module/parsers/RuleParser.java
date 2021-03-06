package nl.tudelft.goalkeeper.parser.results.files.module.parsers;

import languageTools.program.agent.rules.ForallDoRule;
import languageTools.program.agent.rules.ListallDoRule;
import lombok.Getter;
import lombok.Setter;
import nl.tudelft.goalkeeper.checking.violations.source.BlockSource;
import nl.tudelft.goalkeeper.checking.violations.source.CharacterSource;
import nl.tudelft.goalkeeper.checking.violations.source.LineSource;
import nl.tudelft.goalkeeper.exceptions.InvalidKRLanguageException;
import nl.tudelft.goalkeeper.exceptions.UnknownKRLanguageException;
import nl.tudelft.goalkeeper.parser.results.files.module.ModuleRule;
import nl.tudelft.goalkeeper.parser.results.files.module.RuleType;
import nl.tudelft.goalkeeper.parser.results.files.module.actions.Action;
import nl.tudelft.goalkeeper.parser.results.files.module.conditions.Condition;
import nl.tudelft.goalkeeper.parser.results.parts.KRLanguage;

/**
 * Class which parses a rule.
 */
public final class RuleParser {

    @Getter @Setter private ConditionParser conditionParser;
    @Getter @Setter private ActionParser actionParser;

    /**
     * Creates a new RuleParser instance.
     */
    public RuleParser() {
        conditionParser = new ConditionParser();
        actionParser = new ActionParser();
    }

    /**
     * Parses a rule.
     * @param r ModuleRule to parse.
     * @return GOALkeeper rule version of the rule.
     */
    @SuppressWarnings("MethodLength")
    public ModuleRule parse(languageTools.program.agent.rules.Rule r) {
        ModuleRule rule = new ModuleRule(getType(r));
        String fileName = "";
        int startingLine = Integer.MAX_VALUE;
        int endingLine = Integer.MIN_VALUE;
        KRLanguage language = KRLanguage.UNKNOWN;
        for (languageTools.program.agent.msc.MentalLiteral l
                : r.getCondition().getAllLiterals()) {
            try {
                Condition c = conditionParser.parse(l);
                rule.addCondition(c);
                if (c.getSource() != null && c.getSource() instanceof CharacterSource) {
                    CharacterSource source = (CharacterSource) c.getSource();
                    fileName = source.getFile();
                    startingLine = Math.min(startingLine, source.getLine());
                    endingLine = Math.max(endingLine, source.getLine());
                }
                if (c.getKRLanguage() != KRLanguage.UNKNOWN) {
                    language = c.getKRLanguage();
                }
            } catch (UnknownKRLanguageException | InvalidKRLanguageException e) {
                e.printStackTrace();
            }
        }
        for (languageTools.program.agent.actions.Action<?> a : r.getAction().getActions()) {
            try {
                Action action = actionParser.parse(a);
                rule.addAction(action);
                if (action.getSource() != null && action.getSource() instanceof CharacterSource) {
                    CharacterSource source = (CharacterSource) action.getSource();
                    fileName = source.getFile();
                    startingLine = Math.min(startingLine, source.getLine());
                    endingLine = Math.max(endingLine, source.getLine());
                }
            } catch (UnknownKRLanguageException | InvalidKRLanguageException e) {
                e.printStackTrace();
            }
        }
        if (startingLine == endingLine) {
            rule.setSource(new LineSource(fileName, startingLine));
        } else {
            rule.setSource(new BlockSource(fileName, startingLine, endingLine));
        }
        rule.setKRLanguage(language);
        return rule;
    }

    /**
     * Gets the type of a rule.
     * @param r ModuleRule to get the type from.
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
