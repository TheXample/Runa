package edu.kit.informatik.structure.card.abilities.physical.offensive;

import edu.kit.informatik.structure.card.abilities.AbilityType;
import edu.kit.informatik.structure.card.abilities.PhysicalAbility;

/**
 * The type Slash.
 *
 * @author Hannes
 * @version 0.1
 */
public class Slash extends PhysicalAbility {

    private static final String DESCRIPTION = "Deals 4n + dice physical damage and breaks the targets focus.";

    private static final String NAME = "Slash";

    private static final int FOUR = 4;

    /**
     * Instantiates a new Slash.
     *
     * @param abilityLevel the ability level
     */
    public Slash(int abilityLevel) {
        super(NAME, AbilityType.OFFENSIVE, DESCRIPTION, true, abilityLevel);
    }

    @Override
    public int calculate(int dice) {
        return FOUR * this.getAbilityLevel() + dice;
    }

}
