package nl.tudelft.goalkeeper.parser.results.files.mas2g;

import languageTools.program.mas.MASProgram;

import java.io.IOException;

/**
 * Created by Cedric Willekens (4530373) on 2/17/2018.
 */
public final class MasParser {
    private MasParser() { }

    public static Mas2gFile parse(MASProgram mas) throws IOException {
        Mas2gFile result = new Mas2gFile(mas.getSourceFile().toString());
    }
}
