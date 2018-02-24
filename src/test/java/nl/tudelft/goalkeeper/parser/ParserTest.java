package nl.tudelft.goalkeeper.parser;

import languageTools.analyzer.FileRegistry;
import languageTools.analyzer.mas.Analysis;
import languageTools.analyzer.mas.MASValidator;
import languageTools.errors.Message;
import languageTools.program.agent.Module;
import nl.tudelft.goalkeeper.checking.violations.Violation;
import nl.tudelft.goalkeeper.parser.results.ParseResult;
import nl.tudelft.goalkeeper.parser.results.files.File;
import nl.tudelft.goalkeeper.parser.results.files.module.parsers.MessageParser;
import nl.tudelft.goalkeeper.parser.results.files.module.parsers.ModuleParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.HashSet;
import java.util.SortedSet;
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
    private Violation violation;
    private Message message;
    private Analysis analysis;

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
        parser.setValidator(validator);
        parser.setMessageParser(messageParser);
        parser.setModuleParser(moduleParser);
        FileRegistry fileRegistry = Mockito.mock(FileRegistry.class);
        Mockito.when(messageParser.parse(message)).thenReturn(violation);
        Mockito.when(validator.getRegistry()).thenReturn(fileRegistry);
        Mockito.when(fileRegistry.getErrors()).thenReturn(new TreeSet<>());
        Mockito.when(fileRegistry.getSyntaxErrors()).thenReturn(new TreeSet<>());
        Mockito.when(fileRegistry.getWarnings()).thenReturn(new TreeSet<>());
        analysis = Mockito.mock(Analysis.class);
        Mockito.when(validator.process()).thenReturn(analysis);
        Mockito.when(analysis.getModuleDefinitions()).thenReturn(new HashSet<>());
    }

    /**
     * Checks that we can correctly parse a mas file with an error.
     */
    @Test
    void errorTest() {
        validator.getRegistry().getErrors().add(message);
        ParseResult result = parser.parse();
        assertThat(result.isSuccessful()).isFalse();
        assertThat(result.getViolations()).hasSize(1);
        Mockito.verify(messageParser, Mockito.times(1)).parse(message);
        Mockito.verify(violation, Mockito.times(1)).setError(true);
    }

    /**
     * Checks that we can correctly parse a mas file with a syntax error.
     */
    @Test
    void syntaxErrorTest() {
        validator.getRegistry().getSyntaxErrors().add(message);
        ParseResult result = parser.parse();
        assertThat(result.isSuccessful()).isFalse();
        assertThat(result.getViolations()).hasSize(1);
        Mockito.verify(messageParser, Mockito.times(1)).parse(message);
        Mockito.verify(violation, Mockito.times(1)).setError(true);
    }

    /**
     * Checks that we can correctly parse a mas file with a warning.
     */
    @Test
    void warningTest() {
        validator.getRegistry().getWarnings().add(message);
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
