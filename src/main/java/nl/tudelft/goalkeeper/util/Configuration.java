package nl.tudelft.goalkeeper.util;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class holding all program launch parameters.
 */
public final class Configuration {
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
        Pattern pattern = Pattern.compile("(\\w+)(?:=(.+))?");

        for (String arg : args) {
            if (arg.charAt(0) == '-') {
                Matcher matcher = pattern.matcher(arg);
                while (matcher.find()) {
                    String name = matcher.group(1);
                    String value = matcher.group(2);
                    if (value == null) {
                        value = "true";
                    }
                    configuration.addParameter(name, value);
                }
            } else {
                File file = new File(arg);
                if (file.exists() && !file.isDirectory()) {
                    configuration.addFile(file.getAbsolutePath());
                }
            }
        }
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
