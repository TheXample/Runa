package edu.kit.informatik.structure.dice;

import java.util.Random;

public class Dice {

    private static Random RAND;

    private DiceType type;

    public Dice(DiceType type, int seed) {
        this.type = type;
        RAND = new Random(seed);
    }

    public int roll() {
        int roll = RAND.nextInt(type.getValue() + 1);
        return Math.max(roll, 1);
    }

    public DiceType getType() {
        return type;
    }

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
