package nl.tudelft.goalanalyzer.util;

/**
 * Class holding all program launch parameters.
 */
public class Configuration {
    private static Configuration configuration;

    /**
     * Prevents instantiation.
     */
    private Configuration() { }

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
}
