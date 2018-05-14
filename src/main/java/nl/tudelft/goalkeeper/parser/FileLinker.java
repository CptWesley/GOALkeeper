package nl.tudelft.goalkeeper.parser;

import nl.tudelft.goalkeeper.parser.results.ParseResult;
import nl.tudelft.goalkeeper.parser.results.files.actionspec.ActionSpecFile;
import nl.tudelft.goalkeeper.parser.results.files.module.ModuleFile;

import java.util.HashMap;
import java.util.Map;

/**
 * Links all files in the parsed results.
 */
public final class FileLinker {

    /**
     * Links all files in a parse result.
     */
    public void link(ParseResult result) {
        Map<String, ModuleFile> modules = createModuleMap(result);
        Map<String, ActionSpecFile> actionSpecs = createActionSpecMap(result);
    }

    /**
     * Creates a map linking all modules to their paths.
     * @param result Parse result to find all module files of.
     * @return Map of all modules linked to their paths.
     */
    private Map<String, ModuleFile> createModuleMap(ParseResult result) {
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
    private Map<String, ActionSpecFile> createActionSpecMap(ParseResult result) {
        Map<String, ActionSpecFile> actionSpecs = new HashMap<>();

        for (ActionSpecFile file : result.getActionSpecs()) {
            actionSpecs.put(file.getSource().getFile(), file);
        }
        
        return actionSpecs;
    }
}
