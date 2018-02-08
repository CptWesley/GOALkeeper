package nl.tudelft.goalkeeper.parser.queries;

/**
 * Interface for classes having a KRLanguage.
 */
interface Linguistic {
    /**
     * Gets the KRLanguage of the object.
     * @return KRLanguage of the object.
     */
    KRLanguage getKRLanguage();

    /**
     * Sets the KRLanguage of the object.
     * @param language KRLanguage of the object.
     */
    void setKRLanguage(KRLanguage language);
}
