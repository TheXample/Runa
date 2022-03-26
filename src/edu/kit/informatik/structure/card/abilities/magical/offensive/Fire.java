package edu.kit.informatik.structure.card.abilities.magical.offensive;

import edu.kit.informatik.structure.card.abilities.AbilityType;
import edu.kit.informatik.structure.card.abilities.MagicType;
import edu.kit.informatik.structure.card.abilities.MagicAbility;

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

    private static final int TWO = 2;

    private static final int FIVE = 5;

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
            return (TWO * this.getAbilityLevel() + FIVE) * focusPoints + TWO * this.getAbilityLevel();
        }
        return (TWO * this.getAbilityLevel() + FIVE) * focusPoints;
    }
}
