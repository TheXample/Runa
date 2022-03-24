package edu.kit.informatik.abilities;

/**
 * The enum Ability type.
 */
public enum AbilityType {

    /**
     * Offensive ability type.
     */
    OFFENSIVE("offensive"),
    /**
     * Defensive ability type.
     */
    DEFENSIVE("defensive"),
    /**
     * Focus ability type.
     */
    FOCUS("focus"),
    /**
     * Magic ability type.
     */
    MAGIC("mag"),
    /**
     * Physical ability type.
     */
    PHYSICAL("phy");

    private final String value;


    /**
     * inits the value.
     *
     * @param value the value
     */
    AbilityType(final String value) {
        this.value = value;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }
}
