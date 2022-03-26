package edu.kit.informatik.structure.abilities.physical.offensive;

import edu.kit.informatik.structure.abilities.AbilityType;
import edu.kit.informatik.structure.abilities.PhysicalAbility;

/**
 * The type Bite.
 *
 * @author Hannes
 * @version 0.1
 */
public class Bite extends PhysicalAbility {

    private static final String DESCRIPTION = "10n physischer Schaden";

    private static final String NAME = "Bite";

    private static final int TEN = 10;

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
        return TEN * this.getAbilityLevel();
    }

}
