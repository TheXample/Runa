package edu.kit.informatik.structure.dice;

/**
 * The enum Dice type.
 *
 * @author Hanne
 * @version 0.1
 */
public enum DiceType {

    /**
     * D four dice type.
     */
    D_FOUR(4),

    /**
     * D six dice type.
     */
    D_SIX(6),
    /**
     * D eight dice type.
     */
    D_EIGHT(8),
    /**
     * D ten dice type.
     */
    D_TEN(10),
    /**
     * D twelve dice type.
     */
    D_Twelve(12),

    /**
     * D fourteen dice type.
     */
    D_FOURTEEN(14),

    /**
     * D sixteen dice type.
     */
    D_SIXTEEN(16),

    /**
     * D eighttenn dice type.
     */
    D_EIGHTTENN(18),

    /**
     * D twenty dice type.
     */
    D_TWENTY(20),

    /**
     * D twentytwo dice type.
     */
    D_TWENTYTWO(22);


    private final int value;


    /**
     * Inits value.
     *
     * @param value the value
     */
    DiceType(final int value) {
        this.value = value;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public int getValue() {
        return value;
    }
}
