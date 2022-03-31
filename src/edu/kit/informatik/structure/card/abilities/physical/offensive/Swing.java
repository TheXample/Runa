package edu.kit.informatik.structure.card.abilities.physical.offensive;

import edu.kit.informatik.structure.card.abilities.AbilityType;
import edu.kit.informatik.structure.card.abilities.PhysicalAbility;

/**
 * The type Swing.
 *
 * @author Hannes
 * @version 0.1
 */
public class Swing extends PhysicalAbility {

    private static final String DESCRIPTION = "Deals 5n + dice physical damage and breaks the targets focus.";

    private static final String NAME = "Swing";

    private static final int FIVE = 5;

    /**
     * Instantiates a new Swing.
     *
     * @param abilityLevel the ability level
     */
    public Swing(int abilityLevel) {
        super(NAME, AbilityType.OFFENSIVE, DESCRIPTION, true, abilityLevel);
    }

    @Override
    public int calculate(int dice) {
        return FIVE * this.getAbilityLevel() + dice;
    }

}
