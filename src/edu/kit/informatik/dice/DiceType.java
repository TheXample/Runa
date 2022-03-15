package edu.kit.informatik.dice;

public enum DiceType {

    D_SIX(6),
    D_EIGHT(8),
    D_TEN(10),
    D_Twelve(12);


    private final int value;


    DiceType(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
