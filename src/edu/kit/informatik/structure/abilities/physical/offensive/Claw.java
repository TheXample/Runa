package edu.kit.informatik.structure.abilities.physical.offensive;

import edu.kit.informatik.structure.abilities.AbilityType;
import edu.kit.informatik.structure.abilities.PhysicalAbility;

/**
 * The type Claw.
 *
 * @author Hannes
 * @version 0.1
 */
public class Claw extends PhysicalAbility {

    private static final String DESCRIPTION = "6n physischer Schaden, Bricht Runas Focus";

    private static final String NAME = "Claw";

    private static final int SIX = 6;

    /**
     * Instantiates a new Claw.
     *
     * @param abilityLevel the ability level
     */
    public Claw(int abilityLevel) {
        super(NAME, AbilityType.OFFENSIVE, DESCRIPTION, true, abilityLevel);
    }

    @Override
    public int calculate(int dice) {
        return SIX * this.getAbilityLevel();
    }

}
