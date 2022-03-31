package edu.kit.informatik.structure.dice;

import java.util.Random;

/**
 * The type Dice.
 */
public class Dice {

    private static Random RAND;

    private DiceType type;

    /**
     * Instantiates a new Dice.
     *
     * @param type the type
     * @param seed the seed
     */
    public Dice(DiceType type, int seed) {
        this.type = type;
        RAND = new Random(seed);
    }

    /**
     * Roll int.
     *
     * @return the int
     */
    public int roll() {
        int roll = RAND.nextInt(type.getValue() + 1);
        return Math.max(roll, 1);
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public DiceType getType() {
        return type;
    }

    /**
     * Upgrade.
     */
    public void upgrade() {
        switch (type) {
            case D_FOUR -> type = DiceType.D_SIX;
            case D_SIX -> type = DiceType.D_EIGHT;
            case D_EIGHT -> type = DiceType.D_TEN;
            case D_TEN -> type = DiceType.D_Twelve;
            case D_Twelve -> type = DiceType.D_FOURTEEN;
            case D_FOURTEEN -> type = DiceType.D_SIXTEEN;
            case D_EIGHTTENN -> type = DiceType.D_TWENTY;
            case D_SIXTEEN -> type = DiceType.D_EIGHTTENN;
            case D_TWENTY -> type = DiceType.D_TWENTYTWO;
            default -> {}
        }
    }
}
