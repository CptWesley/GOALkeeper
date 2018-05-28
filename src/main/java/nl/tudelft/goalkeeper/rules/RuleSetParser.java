package nl.tudelft.goalkeeper.rules;

import lombok.Getter;
import nl.tudelft.goalkeeper.exceptions.MalformedRulesException;
import nl.tudelft.goalkeeper.exceptions.NotParsedException;
import org.jdom2.Attribute;
import org.jdom2.DataConversionException;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Class which parses json rule sets.
 */
class RuleSetParser {

    private static final String RULESET = "ruleset";
    private static final String PROPERTY = "property";
    private static final String NAME = "name";
    private static final String PROPERTY_NAME_MISSING
            = "Properties should have 'name' attribute specified.";
    private static final String PROPERTY_VALUE_MISSING
            = "Properties should have 'value' attribute specified.";
    private static final String PROPERTY_VALUE = "value";
    private static final String CATEGORY = "category";
    private static final String RULE = "rule";
    private static final String SEVERITY_FORMAT = "Property 'severity' should be an integer.";
    private static final String ENABLED_FORMAT = "Property 'enabled' should be a boolean.";
    private static final String LIMIT_FORMAT = "Property 'limit' should be a double.";

    @Getter private boolean parsed; //NOPMD
    private Map<String, Rule> rules;
    @Getter boolean hasErrorSeverity; //NOPMD
    private int errorSeverity;
    @Getter boolean hasFailOnError; //NOPMD
    private boolean failOnError;

    /**
     * Creates a new RuleSetParser instance.
     */
    RuleSetParser() {
        hasErrorSeverity = false;
        hasFailOnError = false;
        rules = new HashMap<>();
    }

    /**
     * Constructor for the rule set parser.
     * @param content JSON string representing our rule set.
     * @throws MalformedRulesException Thrown when content is not valid json.
     */
    void parse(InputStream content)
            throws MalformedRulesException {
        if (isParsed()) {
            return;
        }
        try {
            Element root = new SAXBuilder().build(content).getRootElement().getChild(RULESET);
            parseRuleset(root);
        } catch (JDOMException | IOException e) {
            throw new MalformedRulesException("Invalid XML file.");
        }
        parsed = true;
    }

    /**
     * Gets the minimal severity level that's considered to be an error.
     * @return Minimal error severity level.
     */
    int getErrorSeverity() {
        if (!isParsed()) {
            throw new NotParsedException();
        }
        return errorSeverity;
    }

    /**
     * Gets the value if we should fail the build when we find an error.
     * @return True if build should fail on error.
     * @throws MalformedRulesException Thrown when format is incorrect.
     */
    boolean getFailOnError() throws MalformedRulesException {
        if (!isParsed()) {
            throw new NotParsedException();
        }
        return failOnError;
    }

    /**
     * Gets the rules from the rule set.
     * @return Rule to parse.
     * @throws MalformedRulesException Thrown when the rules are not properly formatted.
     */
    Map<String, Rule> getRules() throws MalformedRulesException {
        if (!isParsed()) {
            throw new NotParsedException();
        }
        return rules;
    }

    /**
     * Parses a ruleset element.
     * @param element Ruleset element to parse.
     * @throws MalformedRulesException Thrown when something is in the wrong format.
     */
    @SuppressWarnings("MethodLength")
    private void parseRuleset(Element element)
            throws MalformedRulesException {
        for (Element property : element.getChildren(PROPERTY)) {
            Attribute nameAttr = property.getAttribute(NAME);
            if (nameAttr == null) {
                throw new MalformedRulesException(PROPERTY_NAME_MISSING);
            }
            Attribute valueAttr = property.getAttribute(PROPERTY_VALUE);
            if (valueAttr == null) {
                throw new MalformedRulesException(PROPERTY_VALUE_MISSING);
            }
            if (nameAttr.getValue().equals("error-severity")) {
                try {
                    errorSeverity = valueAttr.getIntValue();
                    hasErrorSeverity = true;
                } catch (DataConversionException e) {
                    throw new MalformedRulesException(
                            "Property 'error-severity' should be an integer.");
                }
            } else if (nameAttr.getValue().equals("fail-on-error")) {
                try {
                    failOnError = valueAttr.getBooleanValue();
                    hasFailOnError = true;
                } catch (DataConversionException e) {
                    throw new MalformedRulesException(
                            "Property 'fail-on-error' should be a boolean.");
                }
            }
        }
        for (Element category : element.getChildren(CATEGORY)) {
            parseCategory(category, true, 0, "");
        }
        for (Element rule : element.getChildren(RULE)) {
            parseRule(rule, true, 0, "");
        }
    }

    @SuppressWarnings("MethodLength")
    private void parseCategory(Element element, boolean enabled, int severity, String path)
            throws MalformedRulesException {
        Attribute nameAttr = element.getAttribute(NAME);
        if (nameAttr == null) {
            throw new MalformedRulesException("Category should have a 'name' attribute.");
        }
        path += nameAttr.getValue() + ".";
        for (Element property : element.getChildren(PROPERTY)) {
            Attribute pNameAttr = property.getAttribute(NAME);
            if (pNameAttr == null) {
                throw new MalformedRulesException(PROPERTY_NAME_MISSING);
            }
            Attribute valueAttr = property.getAttribute(PROPERTY_VALUE);
            if (valueAttr == null) {
                throw new MalformedRulesException(PROPERTY_VALUE_MISSING);
            }
            if (pNameAttr.getValue().equals("severity")) {
                try {
                    severity = valueAttr.getIntValue();
                } catch (DataConversionException e) {
                    throw new MalformedRulesException(SEVERITY_FORMAT);
                }
            } else if (pNameAttr.getValue().equals("enabled")) {
                try {
                    enabled = valueAttr.getBooleanValue();
                } catch (DataConversionException e) {
                    throw new MalformedRulesException(ENABLED_FORMAT);
                }
            }
        }
        for (Element category : element.getChildren(CATEGORY)) {
            parseCategory(category, enabled, severity, path);
        }
        for (Element rule : element.getChildren(RULE)) {
            parseRule(rule, enabled, severity, path);
        }
    }

    @SuppressWarnings("MethodLength")
    private void parseRule(Element element, boolean enabled, int severity, String path)
            throws MalformedRulesException {
        Attribute nameAttr = element.getAttribute(NAME);
        if (nameAttr == null) {
            throw new MalformedRulesException("Category should have a 'name' attribute.");
        }
        path += nameAttr.getValue();
        Rule rule = new Rule();
        for (Element property : element.getChildren(PROPERTY)) {
            Attribute pNameAttr = property.getAttribute(NAME);
            if (pNameAttr == null) {
                throw new MalformedRulesException(PROPERTY_NAME_MISSING);
            }
            Attribute valueAttr = property.getAttribute(PROPERTY_VALUE);
            if (valueAttr == null) {
                throw new MalformedRulesException(PROPERTY_VALUE_MISSING);
            }
            if (pNameAttr.getValue().equals("severity")) {
                try {
                    double limit = 0;
                    severity = valueAttr.getIntValue();
                    Attribute limitAttr = property.getAttribute("limit");
                    if (limitAttr != null) {
                        try {
                            limit = limitAttr.getDoubleValue();
                        } catch (DataConversionException e) {
                            throw new MalformedRulesException(LIMIT_FORMAT);
                        }
                    }
                    rule.addStage(new Stage().setLimit(limit).setSeverity(severity));
                } catch (DataConversionException e) {
                    throw new MalformedRulesException(SEVERITY_FORMAT);
                }
            } else if (pNameAttr.getValue().equals("enabled")) {
                try {
                    enabled = valueAttr.getBooleanValue();
                } catch (DataConversionException e) {
                    throw new MalformedRulesException(ENABLED_FORMAT);
                }
            }
            rule.setEnabled(enabled);
        }
        rules.put(path, rule);
    }
}
