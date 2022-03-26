package edu.kit.informatik.structure.abilities.physical.offensive;

import edu.kit.informatik.structure.abilities.AbilityType;
import edu.kit.informatik.structure.abilities.PhysicalAbility;

/**
 * The type Smash.
 *
 * @author Hannes
 * @version 0.1
 */
public class Smash extends PhysicalAbility {

    private static final String DESCRIPTION = "8n physischer Schaden";

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
