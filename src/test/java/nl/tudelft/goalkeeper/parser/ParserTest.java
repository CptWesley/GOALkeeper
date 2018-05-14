package nl.tudelft.goalkeeper.parser;

import languageTools.analyzer.FileRegistry;
import languageTools.analyzer.mas.Analysis;
import languageTools.analyzer.mas.MASValidator;
import languageTools.errors.Message;
import languageTools.program.agent.Module;
import nl.tudelft.goalkeeper.checking.violations.Violation;
import nl.tudelft.goalkeeper.parser.results.ParseResult;
import nl.tudelft.goalkeeper.parser.results.files.mas.parsers.MasParser;
import nl.tudelft.goalkeeper.parser.results.files.module.parsers.MessageParser;
import nl.tudelft.goalkeeper.parser.results.files.module.parsers.ModuleParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.HashSet;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the Parser class.
 */
class ParserTest {

    private Parser parser;
    private MASValidator validator;
    private MessageParser messageParser;
    private ModuleParser moduleParser;
    private MasParser masParser;
    private Violation violation;
    private Message message;
    private Analysis analysis;
    private FileRegistry fileRegistry;

    /**
     * Sets up the testing environment before each test.
     */
    @BeforeEach
    void setup() {
        parser = new Parser("");
        validator = Mockito.mock(MASValidator.class);
        messageParser = Mockito.mock(MessageParser.class);
        violation = Mockito.mock(Violation.class);
        message = Mockito.mock(Message.class);
        moduleParser = Mockito.mock(ModuleParser.class);
        masParser = Mockito.mock(MasParser.class);
        parser.setValidator(validator);
        parser.setMessageParser(messageParser);
        parser.setModuleParser(moduleParser);
        parser.setMasParser(masParser);
        fileRegistry = Mockito.mock(FileRegistry.class);
        Mockito.when(messageParser.parse(message)).thenReturn(violation);
        Mockito.when(validator.getRegistry()).thenReturn(fileRegistry);
        analysis = Mockito.mock(Analysis.class);
        Mockito.when(validator.process()).thenReturn(analysis);
        Mockito.when(analysis.getModuleDefinitions()).thenReturn(new HashSet<>());
    }

    /**
     * Checks that we can correctly parse a mas file with an error.
     */
    @Test
    void errorTest() {
        TreeSet set = new TreeSet<Message>();
        set.add(message);
        Mockito.when(fileRegistry.getAllErrors()).thenReturn(set);
        ParseResult result = parser.parse();
        assertThat(result.getViolations()).hasSize(1);
        assertThat(result.isSuccessful()).isFalse();
        Mockito.verify(messageParser, Mockito.times(1)).parse(message);
        Mockito.verify(violation, Mockito.times(1)).setError(true);
    }

    /**
     * Checks that we can correctly parse a mas file with a syntax error.
     */
    @Test
    void syntaxErrorTest() {
        TreeSet set = new TreeSet<Message>();
        set.add(message);
        Mockito.when(fileRegistry.getAllErrors()).thenReturn(set);
        ParseResult result = parser.parse();
        assertThat(result.getViolations()).hasSize(1);
        assertThat(result.isSuccessful()).isFalse();
        Mockito.verify(messageParser, Mockito.times(1)).parse(message);
        Mockito.verify(violation, Mockito.times(1)).setError(true);
    }

    /**
     * Checks that we can correctly parse a mas file with a warning.
     */
    @Test
    void warningTest() {
        TreeSet set = new TreeSet<Message>();
        set.add(message);
        Mockito.when(fileRegistry.getWarnings()).thenReturn(set);
        ParseResult result = parser.parse();
        assertThat(result.isSuccessful()).isTrue();
        assertThat(result.getViolations()).hasSize(1);
        Mockito.verify(messageParser, Mockito.times(1)).parse(message);
        Mockito.verify(violation, Mockito.times(1)).setError(false);
    }

    /**
     * Add checks for correct files.
     */
    @Test
    void successTest() throws IOException {
        Module m1 = Mockito.mock(Module.class);
        Module m2 = Mockito.mock(Module.class);
        analysis.getModuleDefinitions().add(m1);
        analysis.getModuleDefinitions().add(m2);

        ParseResult result = parser.parse();
        assertThat(result.isSuccessful()).isTrue();
        assertThat(result.isSuccessful()).isTrue();
        assertThat(result.getViolations()).isEmpty();
        assertThat(result.getModules()).hasSize(2);

        Mockito.verify(moduleParser, Mockito.times(1)).parseToFile(m1);
        Mockito.verify(moduleParser, Mockito.times(1)).parseToFile(m2);
    }
}
