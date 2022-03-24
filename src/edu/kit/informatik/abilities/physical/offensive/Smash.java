package edu.kit.informatik.abilities.physical.offensive;

import edu.kit.informatik.abilities.AbilityType;
import edu.kit.informatik.abilities.PhysicalAbility;

/**
 * The type Smash.
 * @author Hannes
 * @version 0.1
 */
public class Smash extends PhysicalAbility {

    private static final String DESCRIPTION = "8n physischer Schaden";

    private static final String NAME = "Smash";

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
        return 8 * this.getAbilityLevel();
    }

}
