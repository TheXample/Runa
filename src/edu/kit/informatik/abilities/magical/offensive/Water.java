package edu.kit.informatik.abilities.magical.offensive;

import edu.kit.informatik.abilities.AbilityType;
import edu.kit.informatik.abilities.MagicType;
import edu.kit.informatik.abilities.magicAbility;

public class Water extends magicAbility{

    private static final String DESCRIPTION = "(2n + 4) ∗ f magischer Schaden " +
            "+ 2n magischer Schaden, falls Ziel ein Blitz-Monster";

    private static final String NAME = "Water";

    private static final int COST = 1;

    public Water(int abilityLevel) {
        super(NAME, AbilityType.OFFENSIVE, DESCRIPTION, false, COST, MagicType.WATER, abilityLevel);
    }

    @Override
    public int calculate(int focusPoints, MagicType opposingType) {
        if (opposingType.equals(MagicType.LIGHTNING)) {
            return (2 * this.getAbilityLevel() + 4) * focusPoints + 2 * this.getAbilityLevel();
        }
        return (2 * this.getAbilityLevel() + 4) * focusPoints;
    }
}
