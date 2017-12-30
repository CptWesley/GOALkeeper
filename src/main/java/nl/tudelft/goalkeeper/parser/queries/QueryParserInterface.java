package nl.tudelft.goalkeeper.parser.queries;

import nl.tudelft.goalkeeper.parser.results.parts.Query;

/**
 * Interface for query parsers.
 */
public interface QueryParserInterface {

    /**
     * Parses original query to GOALkeeper query type.
     * @param query Original GOAL query.
     * @return GOALkeeper query version of the GOAL query.
     */
    Query parse(krTools.language.Query query);
}
