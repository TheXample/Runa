package edu.kit.informatik.structure.card.abilities.physical.offensive;

import edu.kit.informatik.structure.card.abilities.AbilityType;
import edu.kit.informatik.structure.card.abilities.PhysicalAbility;

/**
 * The type Pierce.
 *
 * @author Hannes
 * @version 0.1
 */
public class Pierce extends PhysicalAbility {

    private static final String DESCRIPTION = "Deals 7n + dice physical damage. + 5n physical damage, if dice > 5.";

    private static final String NAME = "Pierce";

    private static final int FIVE = 5;

    private static final int SIX = 6;

    private static final int SEVEN = 7;

    /**
     * Instantiates a new Pierce.
     *
     * @param abilityLevel the ability level
     */
    public Pierce(int abilityLevel) {
        super(NAME, AbilityType.OFFENSIVE, DESCRIPTION, false, abilityLevel);
    }

    @Override
    public int calculate(int dice) {
        if (dice >= SIX) {
            return SEVEN * this.getAbilityLevel() + dice + FIVE * this.getAbilityLevel();
        }
        return SEVEN * this.getAbilityLevel() + dice;
    }

}
