package nl.tudelft.goalanalyzer.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Class holding all program launch parameters.
 */
public class Configuration {
    private static Configuration configuration;

    private Map<String, LaunchVariable> parameters;
    private Set<String> files;

    /**
     * Prevents instantiation.
     */
    private Configuration() {
        parameters = new HashMap<>();
        files = new HashSet<>();
    }

    /**
     * Get the configuration instance.
     * @return The sole configuration instance.
     */
    public static synchronized Configuration getInstance() {
        if (configuration == null) {
            configuration = new Configuration();
        }
        return configuration;
    }

    /**
     * Clears the configuration instance.
     */
    public static synchronized void clear() {
        configuration = null;
    }

    /**
     * Load configuration from launch arguments.
     * @param args Launch arguments.
     * @return New configuration instance.
     */
    public static synchronized Configuration load(String[] args) {
        Configuration configuration = Configuration.getInstance();
        return configuration;
    }

    /**
     * Adds a parameter to the parameter list.
     * @param name Name of the parameter.
     * @param value Value of the parameter.
     * @return Current configuration.
     */
    public Configuration addParameter(String name, String value) {
        parameters.put(name, new LaunchVariable(value));
        return this;
    }

    /**
     * Gets specified parameter.
     * @param name Name of the parameter.
     * @return Value of the parameter.
     */
    public LaunchVariable getParameter(String name) {
        return parameters.get(name);
    }

    /**
     * Checks if specified parameter exists.
     * @param name Name of the parameter.
     * @return True if parameter name is contained
     */
    public boolean hasParameter(String name) {
        return getParameter(name) != null;
    }

    /**
     * Adds the specified file name to the file list.
     * @param fileName File name to add.
     * @return This configuration.
     */
    public Configuration addFile(String fileName) {
        files.add(fileName);
        return this;
    }

    /**
     * Gets the list of all files.
     * @return List of all files.
     */
    public String[] getFiles() {
        return files.toArray(new String[files.size()]);
    }
}
