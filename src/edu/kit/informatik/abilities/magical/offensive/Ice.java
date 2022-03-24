package edu.kit.informatik.abilities.magical.offensive;

import edu.kit.informatik.abilities.AbilityType;
import edu.kit.informatik.abilities.MagicType;
import edu.kit.informatik.abilities.MagicAbility;

/**
 * The type Ice.
 * @author Hannes
 * @version 0.1
 */
public class Ice extends MagicAbility {

    private static final String DESCRIPTION = "(2n + 4) ∗ f + 2 magischer Schaden "
            + "+ 2n magischer Schaden, falls Ziel ein Wasser-Monster";

    private static final String NAME = "Ice";

    private static final int COST = 1;

    /**
     * Instantiates a new Ice.
     *
     * @param abilityLevel the ability level
     */
    public Ice(int abilityLevel) {
        super(NAME, AbilityType.OFFENSIVE, DESCRIPTION, false, COST, MagicType.ICE, abilityLevel);
    }

    @Override
    public int calculate(int focusPoints, MagicType opposingType) {
        if (opposingType.equals(MagicType.LIGHTNING)) {
            return (2 * this.getAbilityLevel() + 4) * focusPoints + 2 + 2 * this.getAbilityLevel();
        }
        return (2 * this.getAbilityLevel() + 4) * focusPoints + 2;
    }
}
