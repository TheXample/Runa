package edu.kit.informatik.characters;

import edu.kit.informatik.abilities.Ability;
import edu.kit.informatik.dice.Dice;
import edu.kit.informatik.dice.DiceType;

import java.util.List;

public class Runa extends Character{

    private static final int maxHealth = 50;

    private static final int minFocus = 1;

    private Dice dice;

    public Runa(String name, List<Ability> abilities) {
        super(name, maxHealth , minFocus, abilities);
        this.dice = new Dice(DiceType.D_SIX);
    }

    public int rollDice(int seed) {
        return dice.roll(seed);
    }

    public boolean upgradeDice() {
        switch (dice.getType()) {
            case D_SIX -> {
                dice = new Dice(DiceType.D_EIGHT);
                return true;
            }
            case D_EIGHT -> {
                dice = new Dice(DiceType.D_TEN);
                return true;
            }
            case D_TEN -> {
                dice = new Dice(DiceType.D_Twelve);
                return true;
            }
        }
        return false;
    }
}
