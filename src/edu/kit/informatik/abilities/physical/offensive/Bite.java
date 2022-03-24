package edu.kit.informatik.abilities.physical.offensive;

import edu.kit.informatik.abilities.AbilityType;
import edu.kit.informatik.abilities.PhysicalAbility;

/**
 * The type Bite.
 *
 * @author Hannes
 * @version 0.1
 */
public class Bite extends PhysicalAbility {

    private static final String DESCRIPTION = "10n physischer Schaden";

    private static final String NAME = "Bite";

    /**
     * Instantiates a new Bite.
     *
     * @param abilityLevel the ability level
     */
    public Bite(int abilityLevel) {
        super(NAME, AbilityType.OFFENSIVE, DESCRIPTION, false, abilityLevel);
    }

    @Override
    public int calculate(int dice) {
        return 10 * this.getAbilityLevel();
    }

}
