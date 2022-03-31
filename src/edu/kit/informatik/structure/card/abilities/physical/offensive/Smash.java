package edu.kit.informatik.structure.card.abilities.physical.offensive;

import edu.kit.informatik.structure.card.abilities.AbilityType;
import edu.kit.informatik.structure.card.abilities.PhysicalAbility;

/**
 * The type Smash.
 *
 * @author Hannes
 * @version 0.1
 */
public class Smash extends PhysicalAbility {

    private static final String DESCRIPTION = "Deals 8n physical damage to the target.";

    private static final String NAME = "Smash";

    private static final int EIGHT = 8;

    /**
     * Instantiates a new Smash.
     *
     * @param abilityLevel the ability level
     */
    public Smash(int abilityLevel) {
        super(NAME, AbilityType.OFFENSIVE, DESCRIPTION, false, abilityLevel);
    }

    @Override
    public int calculate(int dice) {
        return EIGHT * this.getAbilityLevel();
    }

}
