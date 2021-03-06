package nl.tudelft.goalkeeper.parser.results.files.module.parsers;

import lombok.Getter;
import lombok.Setter;
import nl.tudelft.goalkeeper.checking.violations.source.LineSource;
import nl.tudelft.goalkeeper.parser.results.files.module.Module;
import nl.tudelft.goalkeeper.parser.results.files.module.ModuleFile;
import nl.tudelft.goalkeeper.parser.results.files.module.ModuleRule;
import nl.tudelft.goalkeeper.parser.results.files.module.SubModule;
import nl.tudelft.goalkeeper.parser.results.files.module.details.EvaluationOrder;
import nl.tudelft.goalkeeper.parser.results.files.module.details.EvaluationOrderType;
import nl.tudelft.goalkeeper.parser.results.files.module.details.ExitCondition;
import nl.tudelft.goalkeeper.parser.results.files.module.details.ExitConditionType;
import nl.tudelft.goalkeeper.parser.results.parts.KRLanguage;

import java.io.IOException;
import java.util.List;

/**
 * Class that parses module files.
 */
public final class ModuleParser {

    @Getter @Setter private RuleParser ruleParser;

    /**
     * Creates a ModuleParser instance.
     */
    public ModuleParser() {
        ruleParser = new RuleParser();
    }

    /**
     * Parses a GOAL module to a GOALkeeper module file.
     * @param m Module to parse.
     * @return GOALkeeper module file.
     * @throws IOException Thrown when there is a problem reading the file.
     */
    public ModuleFile parseToFile(languageTools.program.agent.Module m) throws IOException {
        ModuleFile module = new ModuleFile(m.getSourceFile().toString());
        addRules(module, m);
        module.setName(m.getName());
        module.setKRLanguage(getKRLanguage(module.getRules()));
        setDetails(module, m);
        return module;
    }

    /**
     * Finds the KRLanguage of a set of rules.
     * @param rules List of rules to find the KRLanguage of.
     * @return The KRLanguage of the rules list.
     */
    private static KRLanguage getKRLanguage(List<ModuleRule> rules) {
        for (ModuleRule rule : rules) {
            if (rule.getKRLanguage() != KRLanguage.UNKNOWN) {
                return rule.getKRLanguage();
            }
        }
        return KRLanguage.UNKNOWN;
    }

    /**
     * Sets the details of a module file.
     * @param mf ModuleFile to set the details for.
     * @param m Module to get the details from.
     */
    private static void setDetails(ModuleFile mf, languageTools.program.agent.Module m) {
        ExitCondition exitCondition
                = new ExitCondition(ExitConditionType.get(m.getExitCondition()));
        mf.setExitCondition(exitCondition);
        EvaluationOrder evaluationOrder
                = new EvaluationOrder(EvaluationOrderType.get(m.getRuleEvaluationOrder()));
        mf.setEvaluationOrder(evaluationOrder);
        String exitPattern
                = String.format(".*(^|\\.)\\s*exit\\s*=\\s*%s\\s*\\..*",
                exitCondition.getType().getName());
        String orderPattern
                = String.format(".*(^|\\.)\\s*order\\s*=\\s*%s\\s*\\..*",
                evaluationOrder.getType().getName());
        for (int i = 0; i < mf.getContent().length; ++i) {
            String line = mf.getContent()[i];
            if (!exitCondition.hasSource() && line.matches(exitPattern)) {
                exitCondition.setSource(new LineSource(mf.getSource().getFile(), i + 1));
            }
            if (!evaluationOrder.hasSource() && line.matches(orderPattern)) {
                evaluationOrder.setSource(new LineSource(mf.getSource().getFile(), i + 1));
            }
        }
    }

    /**
     * Parses a GOAL module to a GOALkeeper submodule.
     * @param m Module to parse.
     * @return GOALkeeper submodule.
     */
    public SubModule parseToSubModule(languageTools.program.agent.Module m) {
        SubModule module = new SubModule();
        addRules(module, m);
        return module;
    }

    /**
     * Parses and adds rules to the corresponding module.
     * @param target Target module.
     * @param source Source module type.
     */
    private void addRules(Module target, languageTools.program.agent.Module source) {
        for (languageTools.program.agent.rules.Rule r : source.getRules()) {
            target.addRule(ruleParser.parse(r));
        }
    }
}
