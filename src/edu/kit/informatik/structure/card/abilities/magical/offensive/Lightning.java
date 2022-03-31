package edu.kit.informatik.structure.card.abilities.magical.offensive;

import edu.kit.informatik.structure.card.abilities.AbilityType;
import edu.kit.informatik.structure.card.abilities.MagicType;
import edu.kit.informatik.structure.card.abilities.MagicAbility;

/**
 * The type Lightning.
 *
 * @author Hannes
 * @version 0.1
 */
public class Lightning extends MagicAbility {

    private static final String DESCRIPTION = "Deals (2n + 5) * FP + 2 magic"
            + " damage from type Lightning. + 2n magic damage, if the target is from type FIRE.";

    private static final String NAME = "Lightning";

    private static final int COST = 1;

    private static final int TWO = 2;

    private static final int FIVE = 5;

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
        if (opposingType.equals(MagicType.FIRE)) {
            return (TWO * this.getAbilityLevel() + FIVE) * focusPoints + TWO + TWO * this.getAbilityLevel();
        }
        return (TWO * this.getAbilityLevel() + FIVE) * focusPoints + TWO;
    }
}
