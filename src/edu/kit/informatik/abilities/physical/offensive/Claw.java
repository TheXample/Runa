package edu.kit.informatik.abilities.physical.offensive;

import edu.kit.informatik.abilities.AbilityType;
import edu.kit.informatik.abilities.PhysicalAbility;

/**
 * The type Claw.
 *
 * @author Hannes
 * @version 0.1
 */
public class Claw extends PhysicalAbility {

    private static final String DESCRIPTION = "6n physischer Schaden, Bricht Runas Focus";

    private static final String NAME = "Claw";

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
        return 6 * this.getAbilityLevel();
    }

}
