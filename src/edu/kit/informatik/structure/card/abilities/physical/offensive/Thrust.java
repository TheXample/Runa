package edu.kit.informatik.structure.card.abilities.physical.offensive;

import edu.kit.informatik.structure.card.abilities.AbilityType;
import edu.kit.informatik.structure.card.abilities.PhysicalAbility;

/**
 * The type Thrust.
 *
 * @author Hannes
 * @version 0.1
 */
public class Thrust extends PhysicalAbility {

    private static final String DESCRIPTION = "Deals 6n + dice physical damage. + 4n physical damage, if dice > 5.";

    private static final String NAME = "Thrust";

    private static final int SIX = 6;

    private static final int FOUR = 4;

    /**
     * Instantiates a new Thrust.
     *
     * @param abilityLevel the ability level
     */
    public Thrust(int abilityLevel) {
        super(NAME, AbilityType.OFFENSIVE, DESCRIPTION, false, abilityLevel);
    }

    @Override
    public int calculate(int dice) {
        if (dice >= SIX) {
            return SIX * this.getAbilityLevel() + dice + FOUR * this.getAbilityLevel();
        }
        return SIX * this.getAbilityLevel() + dice;
    }

}
