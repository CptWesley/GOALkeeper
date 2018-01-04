package nl.tudelft.goalkeeper.parser.results.files.module.parsers;

import nl.tudelft.goalkeeper.parser.results.files.module.Module;
import nl.tudelft.goalkeeper.parser.results.files.module.ModuleFile;
import nl.tudelft.goalkeeper.parser.results.files.module.SubModule;

import java.io.IOException;

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
        return module;
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
            System.out.println(RuleParser.parse(r));
        }
    }
}
