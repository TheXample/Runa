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
        if (roll < 1) return 1;
        return roll;
    }

    public DiceType getType() {
        return type;
    }

    public void upgrade() {
        switch (type) {
            case D_FOUR: {
                type = DiceType.D_SIX;
                return;
            }
            case D_SIX: {
                type = DiceType.D_EIGHT;
                return;
            }
            case D_EIGHT: {
                type = DiceType.D_TEN;
                return;
            }
            case D_TEN: {
                type = DiceType.D_Twelve;
                return;
            }
            default: {
                break;
            }
        }
    }
}
