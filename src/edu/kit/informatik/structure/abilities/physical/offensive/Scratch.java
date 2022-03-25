package edu.kit.informatik.structure.abilities.physical.offensive;

import edu.kit.informatik.structure.abilities.AbilityType;
import edu.kit.informatik.structure.abilities.PhysicalAbility;

/**
 * The type Scratch.
 *
 * @author Hannes
 * @version 0.1
 */
public class Scratch extends PhysicalAbility {

    private static final String DESCRIPTION = "5n physischer Schaden, Bricht Runas Focus";

    private static final String NAME = "Scratch";

    /**
     * Instantiates a new Scratch.
     *
     * @param abilityLevel the ability level
     */
    public Scratch(int abilityLevel) {
        super(NAME, AbilityType.OFFENSIVE, DESCRIPTION, true, abilityLevel);
    }

    @Override
    public int calculate(int dice) {
        return 5 * this.getAbilityLevel();
    }

}
