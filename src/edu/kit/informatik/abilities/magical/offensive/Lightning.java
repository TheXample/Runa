package edu.kit.informatik.abilities.magical.offensive;

import edu.kit.informatik.abilities.AbilityType;
import edu.kit.informatik.abilities.MagicType;
import edu.kit.informatik.abilities.MagicAbility;

/**
 * The type Lightning.
 *
 * @author Hannes
 * @version 0.1
 */
public class Lightning extends MagicAbility {

    private static final String DESCRIPTION = "(2n + 5) ∗ f + 2 magischer Schaden "
            + "+ 2n magischer Schaden, falls Ziel ein Feuer-Monster";

    private static final String NAME = "Lightning";

    private static final int COST = 1;

    /**
     * Instantiates a new Lightning.
     *
     * @param abilityLevel the ability level
     */
    public Lightning(int abilityLevel) {
        super(NAME, AbilityType.OFFENSIVE, DESCRIPTION, false, COST, MagicType.LIGHTNING, abilityLevel);
    }

    @Override
    public int calculate(int focusPoints, MagicType opposingType) {
        if (opposingType.equals(MagicType.LIGHTNING)) {
            return (2 * this.getAbilityLevel() + 5) * focusPoints + 2 + 2 * this.getAbilityLevel();
        }
        return (2 * this.getAbilityLevel() + 5) * focusPoints + 2;
    }
}
