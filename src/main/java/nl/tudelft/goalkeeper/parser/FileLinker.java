package nl.tudelft.goalkeeper.parser;

import nl.tudelft.goalkeeper.parser.results.ParseResult;
import nl.tudelft.goalkeeper.parser.results.files.actionspec.ActionSpecFile;
import nl.tudelft.goalkeeper.parser.results.files.mas.AgentDefinition;
import nl.tudelft.goalkeeper.parser.results.files.mas.MasFile;
import nl.tudelft.goalkeeper.parser.results.files.mas.ModuleUsageDefinition;
import nl.tudelft.goalkeeper.parser.results.files.module.Module;
import nl.tudelft.goalkeeper.parser.results.files.module.ModuleFile;
import nl.tudelft.goalkeeper.parser.results.files.module.ModuleRule;
import nl.tudelft.goalkeeper.parser.results.files.module.actions.Action;
import nl.tudelft.goalkeeper.parser.results.files.module.actions.ExternalAction;
import nl.tudelft.goalkeeper.parser.results.files.module.actions.ModuleAction;
import nl.tudelft.goalkeeper.parser.results.files.module.actions.SubModuleAction;

import java.util.HashMap;
import java.util.Map;

/**
 * Links all files in the parsed results.
 */
public final class FileLinker {

    /**
     * Links all files in a parse result.
     * @param result ParseResult to link files in.
     */
    public void link(ParseResult result) {
        Map<String, ModuleFile> modules = createModuleMap(result);
        Map<String, ActionSpecFile> actionSpecs = createActionSpecMap(result);

        linkMas(result.getMasFile(), modules);

        for (ModuleFile module : result.getModules()) {
            linkModule(module, modules, actionSpecs);
        }
    }

    /**
     * Creates a map linking all modules to their paths.
     * @param result Parse result to find all module files of.
     * @return Map of all modules linked to their paths.
     */
    public Map<String, ModuleFile> createModuleMap(ParseResult result) {
        Map<String, ModuleFile> modules = new HashMap<>();

        for (ModuleFile file : result.getModules()) {
            modules.put(file.getSource().getFile(), file);
        }

        return modules;
    }

    /**
     * Creates a map linking all actionspecs to their paths.
     * @param result Parse result to find all actionspec files of.
     * @return Map of all actionspecs linked to their paths.
     */
    public Map<String, ActionSpecFile> createActionSpecMap(ParseResult result) {
        Map<String, ActionSpecFile> actionSpecs = new HashMap<>();

        for (ActionSpecFile file : result.getActionSpecs()) {
            actionSpecs.put(file.getSource().getFile(), file);
        }

        return actionSpecs;
    }

    /**
     * Link the mas file.
     * @param mas Mas file to link in.
     * @param modules Module map.
     */
    public void linkMas(MasFile mas, Map<String, ModuleFile> modules) {
        for (AgentDefinition agent : mas.getAgentDefinitions()) {
            ModuleUsageDefinition event = agent.getEventModule();
            ModuleUsageDefinition init = agent.getInitModule();
            ModuleUsageDefinition main = agent.getMainModule();
            ModuleUsageDefinition shutdown = agent.getShutDownModule();

            event.setModule(modules.get(event.getTarget()));
            init.setModule(modules.get(init.getTarget()));
            main.setModule(modules.get(main.getTarget()));
            shutdown.setModule(modules.get(shutdown.getTarget()));
        }
    }

    /**
     * Link the module files.
     * @param module Module to link in.
     * @param modules Module map.
     * @param actionSpecs ActionSpec map.
     */
    public void linkModule(Module module, Map<String,
            ModuleFile> modules, Map<String, ActionSpecFile> actionSpecs) {
        for (ModuleRule rule : module.getRules()) {
            for (Action a1 : rule.getActions()) {
                if (a1 instanceof ModuleAction) {
                    ModuleAction a2 = (ModuleAction) a1;
                    a2.setModule(modules.get(a2.getTarget()));
                } else if (a1 instanceof SubModuleAction) {
                    SubModuleAction a2 = (SubModuleAction) a1;
                    linkModule(a2.getModule(), modules, actionSpecs);
                } else if (a1 instanceof ExternalAction) {
                    ExternalAction a2 = (ExternalAction) a1;
                    a2.setAction(actionSpecs.get(a2.getTarget()));
                }
            }
        }
    }
}
