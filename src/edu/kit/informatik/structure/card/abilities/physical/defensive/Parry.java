package edu.kit.informatik.structure.card.abilities.physical.defensive;

import edu.kit.informatik.structure.card.abilities.AbilityType;
import edu.kit.informatik.structure.card.abilities.PhysicalAbility;

/**
 * The type Parry.
 *
 * @author Hannes
 * @version 0.1
 */
public class Parry extends PhysicalAbility {

    private static final String DESCRIPTION = "Reduces the incoming physical damage by 10n.";

    private static final String NAME = "Parry";

    private static final int ZERO = 0;

    private static final int TEN = 10;

    /**
     * Instantiates a new Parry.
     *
     * @param abilityLevel the ability level
     */
    public Parry(int abilityLevel) {
        super(NAME, AbilityType.DEFENSIVE, DESCRIPTION, false, abilityLevel);
    }

    @Override
    public int calculate(int damage) {
        if (damage >= TEN * getAbilityLevel()) {
            return damage - TEN * getAbilityLevel();
        }
        return ZERO;
    }

}
