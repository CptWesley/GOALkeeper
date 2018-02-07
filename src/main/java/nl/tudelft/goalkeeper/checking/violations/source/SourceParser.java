package nl.tudelft.goalkeeper.checking.violations.source;

import krTools.parser.SourceInfo;

/**
 * Class which parses sources.
 */
public final class SourceParser {

    /**
     * Prevents instantiation.
     */
    private SourceParser() { }

    /**
     * Parses a GOAL SourceInfo object to a GOALkeeper Source object.
     * @param sourceInfo SourceInfo object to parse.
     * @return Source object result.
     */
    public static Source parse(SourceInfo sourceInfo) {
        String file = sourceInfo.getSource();
        int line = sourceInfo.getLineNumber();
        int position = sourceInfo.getCharacterPosition();
        return new CharacterSource(file, line, position);
    }
}
