package edu.kit.informatik.abilities;

public enum AbilityType {

    OFFENSIVE("offensive"),
    DEFENSIVE("defensive"),
    FOCUS("focus"),
    MAGIC("mgc"),
    PHYSICAL("phy");

    private final String value;


    AbilityType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
