package nl.tudelft.goalanalyzer.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import nl.tudelft.goalanalyzer.exceptions.MalformedRulesException;

/**
 * Wrapped Gson parsing.
 */
public final class VerifiedJsonParser {

    /**
     * Prevents instantiation.
     */
    private VerifiedJsonParser() { }

    /**
     * Gets the integer value of child the child with name from the given element.
     * @param object Object to get the child from.
     * @param name Name of the child.
     * @return Integer value at the child.
     * @throws MalformedRulesException Thrown when the json is not formatted properly.
     */
    public static int getInteger(JsonObject object, String name)
            throws MalformedRulesException {
        JsonElement element = getElement(object, name);
        try {
            return Integer.parseInt(element.toString());
        } catch (NumberFormatException e) {
            throw new MalformedRulesException("Invalid '" + name + "' type.");
        }
    }

    /**
     * Gets the boolean value of child the child with name from the given element.
     * @param object Object to get the child from.
     * @param name Name of the child.
     * @return Integer value at the child.
     * @throws MalformedRulesException Thrown when the json is not formatted properly.
     */
    public static boolean getBoolean(JsonObject object, String name)
            throws MalformedRulesException {
        JsonElement element = getElement(object, name);
        return Boolean.parseBoolean(element.toString());
    }

    /**
     * Gets the string value of child the child with name from the given element.
     * @param object Object to get the child from.
     * @param name Name of the child.
     * @return Integer value at the child.
     * @throws MalformedRulesException Thrown when the json is not formatted properly.
     */
    public static String getString(JsonObject object, String name)
            throws MalformedRulesException {
        JsonElement element = getElement(object, name);
        return element.toString();
    }

    /**
     * Gets the child element of an object.
     * @param object Object to get the child from.
     * @param name Name of the child.
     * @return The child element.
     * @throws MalformedRulesException Thrown when child can not be found.
     */
    private static JsonElement getElement(JsonObject object, String name)
            throws MalformedRulesException {
        JsonElement element = object.get(name);
        if (element == null) {
            throw new MalformedRulesException("Missing '" + name + "' setting.");
        }
        return element;
    }
}
