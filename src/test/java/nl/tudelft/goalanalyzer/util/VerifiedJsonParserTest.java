package nl.tudelft.goalanalyzer.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import nl.tudelft.goalanalyzer.exceptions.MalformedRulesException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Test class for the VerifiedJsonParser class.
 */
class VerifiedJsonParserTest {

    /**
     * Checks that correct integers are correctly retrieved.
     * @throws MalformedRulesException Should never be thrown.
     */
    @Test
    void validIntegerTest() throws MalformedRulesException {
        JsonObject object = Mockito.mock(JsonObject.class);
        JsonElement element = Mockito.mock(JsonElement.class);
        Mockito.when(object.get(Mockito.anyString())).thenReturn(element);
        Mockito.when(element.toString()).thenReturn("3342");
        assertThat(VerifiedJsonParser.getInteger(object, "bla")).isEqualTo(3342);
        Mockito.verify(object, Mockito.times(1)).get(Mockito.anyString());
    }

    /**
     * Checks that invalid integers throw exceptions.
     * @throws MalformedRulesException Should never be thrown.
     */
    @Test
    void invalidIntegerTest() throws MalformedRulesException {
        JsonObject object = Mockito.mock(JsonObject.class);
        JsonElement element = Mockito.mock(JsonElement.class);
        Mockito.when(object.get(Mockito.anyString())).thenReturn(element);
        Mockito.when(element.toString()).thenReturn("dfsg");
        assertThatThrownBy(() -> VerifiedJsonParser.getInteger(object, "bla2"))
                .isInstanceOf(MalformedRulesException.class)
                .hasMessage("Invalid 'bla2' type.");
        Mockito.verify(object, Mockito.times(1)).get(Mockito.anyString());
    }

    /**
     * Checks that missing integers throw exceptions.
     * @throws MalformedRulesException Should never be thrown.
     */
    @Test
    void missingIntegerTest() throws MalformedRulesException {
        JsonObject object = Mockito.mock(JsonObject.class);
        assertThatThrownBy(() -> VerifiedJsonParser.getInteger(object, "bla3"))
                .isInstanceOf(MalformedRulesException.class)
                .hasMessage("Missing 'bla3' setting.");
        Mockito.verify(object, Mockito.times(1)).get(Mockito.anyString());
    }

    /**
     * Checks that correct booleans are correctly retrieved.
     * @throws MalformedRulesException Should never be thrown.
     */
    @Test
    void validBooleanTest() throws MalformedRulesException {
        JsonObject object = Mockito.mock(JsonObject.class);
        JsonElement element = Mockito.mock(JsonElement.class);
        Mockito.when(object.get(Mockito.anyString())).thenReturn(element);
        Mockito.when(element.toString()).thenReturn("true");
        assertThat(VerifiedJsonParser.getBoolean(object, "bla")).isTrue();
        Mockito.verify(object, Mockito.times(1)).get(Mockito.anyString());
    }

    /**
     * Checks that missing booleans throw exceptions.
     * @throws MalformedRulesException Should never be thrown.
     */
    @Test
    void missingBooleanTest() throws MalformedRulesException {
        JsonObject object = Mockito.mock(JsonObject.class);
        assertThatThrownBy(() -> VerifiedJsonParser.getBoolean(object, "bla3"))
                .isInstanceOf(MalformedRulesException.class)
                .hasMessage("Missing 'bla3' setting.");
        Mockito.verify(object, Mockito.times(1)).get(Mockito.anyString());
    }

    /**
     * Checks that correct strings are correctly retrieved.
     * @throws MalformedRulesException Should never be thrown.
     */
    @Test
    void validStringTest() throws MalformedRulesException {
        JsonObject object = Mockito.mock(JsonObject.class);
        JsonElement element = Mockito.mock(JsonElement.class);
        Mockito.when(object.get(Mockito.anyString())).thenReturn(element);
        Mockito.when(element.getAsString()).thenReturn("answer");
        assertThat(VerifiedJsonParser.getString(object, "bla")).isEqualTo("answer");
        Mockito.verify(object, Mockito.times(1)).get(Mockito.anyString());
    }

    /**
     * Checks that missing strings throw exceptions.
     * @throws MalformedRulesException Should never be thrown.
     */
    @Test
    void missingStringTest() throws MalformedRulesException {
        JsonObject object = Mockito.mock(JsonObject.class);
        assertThatThrownBy(() -> VerifiedJsonParser.getString(object, "bla3"))
                .isInstanceOf(MalformedRulesException.class)
                .hasMessage("Missing 'bla3' setting.");
        Mockito.verify(object, Mockito.times(1)).get(Mockito.anyString());
    }
}
