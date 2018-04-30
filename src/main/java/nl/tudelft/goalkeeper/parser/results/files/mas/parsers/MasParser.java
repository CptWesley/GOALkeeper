package nl.tudelft.goalkeeper.parser.results.files.mas.parsers;

import languageTools.program.mas.MASProgram;
import nl.tudelft.goalkeeper.parser.results.files.mas.Mas;

import java.io.IOException;

/**
 * Class which parses mas2g files.
 */
public final class MasParser {

    /**
     * Parses a GOAL MASProgram to a Mas.
     * @param program Program to parse.
     * @return GOALKeeper variant of the MASProgram.
     * @throws IOException Thrown when there is a problem reading the file.
     */
    public Mas parse(MASProgram program) throws IOException {
        Mas mas = new Mas(program.getSourceFile().toString());
        return mas;
    }
}
