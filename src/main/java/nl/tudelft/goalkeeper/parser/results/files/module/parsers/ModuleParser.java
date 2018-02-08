package nl.tudelft.goalkeeper.parser.results.files.module.parsers;

import nl.tudelft.goalkeeper.parser.results.files.module.Module;
import nl.tudelft.goalkeeper.parser.results.files.module.ModuleFile;
import nl.tudelft.goalkeeper.parser.results.files.module.Rule;
import nl.tudelft.goalkeeper.parser.results.files.module.SubModule;
import nl.tudelft.goalkeeper.parser.results.parts.KRLanguage;

import java.io.IOException;
import java.util.List;

/**
 * Class that parses module files.
 */
public final class ModuleParser {

    /**
     * Prevents instantiation.
     */
    private ModuleParser() { }

    /**
     * Parses a GOAL module to a GOALkeeper module file.
     * @param m Module to parse.
     * @return GOALkeeper module file.
     * @throws IOException Thrown when there is a problem reading the file.
     */
    public static ModuleFile parseToFile(languageTools.program.agent.Module m) throws IOException {
        ModuleFile module = new ModuleFile(m.getSourceFile().toString());
        addRules(module, m);
        module.setName(m.getName());
        module.setKRLanguage(getKRLanguage(module.getRules()));
        return module;
    }

    /**
     * Finds the KRLanguage of a set of rules.
     * @param rules List of rules to find the KRLanguage of.
     * @return The KRLanguage of the rules list.
     */
    private static KRLanguage getKRLanguage(List<Rule> rules) {
        for (Rule rule : rules) {
            if (rule.getKRLanguage() != KRLanguage.UNKNOWN) {
                return rule.getKRLanguage();
            }
        }
        return KRLanguage.UNKNOWN;
    }

    /**
     * Parses a GOAL module to a GOALkeeper submodule.
     * @param m Module to parse.
     * @return GOALkeeper submodule.
     */
    public static SubModule parseToSubModule(languageTools.program.agent.Module m) {
        SubModule module = new SubModule();
        addRules(module, m);
        return module;
    }

    /**
     * Parses and adds rules to the corresponding module.
     * @param target Target module.
     * @param source Source module type.
     */
    private static void addRules(Module target, languageTools.program.agent.Module source) {
        for (languageTools.program.agent.rules.Rule r : source.getRules()) {
            target.addRule(RuleParser.parse(r));
        }
    }
}
