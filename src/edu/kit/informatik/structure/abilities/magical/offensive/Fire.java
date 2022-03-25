package edu.kit.informatik.structure.abilities.magical.offensive;

import edu.kit.informatik.structure.abilities.AbilityType;
import edu.kit.informatik.structure.abilities.MagicType;
import edu.kit.informatik.structure.abilities.MagicAbility;

/**
 * The type Fire.
 *
 * @author Hannes
 * @version 0.1
 */
public class Fire extends MagicAbility {

    private static final String DESCRIPTION = "(2n + 5) ∗ f magischer Schaden "
            + "+ 2n magischer Schaden, falls Ziel ein Eis-Monster";

    private static final String NAME = "Fire";

    private static final int COST = 1;

    /**
     * Instantiates a new Fire.
     *
     * @param abilityLevel the ability level
     */
    public Fire(int abilityLevel) {
        super(NAME, AbilityType.OFFENSIVE, DESCRIPTION, false, COST, MagicType.FIRE, abilityLevel);
    }

    @Override
    public int calculate(int focusPoints, MagicType opposingType) {
        if (opposingType.equals(MagicType.LIGHTNING)) {
            return (2 * this.getAbilityLevel() + 5) * focusPoints + 2 * this.getAbilityLevel();
        }
        return (2 * this.getAbilityLevel() + 5) * focusPoints;
    }
}
