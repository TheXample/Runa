package edu.kit.informatik.dice;

import java.util.Random;

public class Dice {

    private DiceType type;

    public Dice(DiceType type) {
        this.type = type;
    }

    public void setType(DiceType type) {
        this.type = type;
    }

    public int roll(int seed) {
        Random rand = new Random(seed);
        return rand.nextInt(1, type.getValue());
    }

    public DiceType getType() {
        return type;
    }
}
