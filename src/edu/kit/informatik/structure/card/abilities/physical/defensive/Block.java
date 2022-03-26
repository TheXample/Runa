package edu.kit.informatik.structure.card.abilities.physical.defensive;

import edu.kit.informatik.structure.card.abilities.AbilityType;
import edu.kit.informatik.structure.card.abilities.PhysicalAbility;

/**
 * The type Parry.
 *
 * @author Hannes
 * @version 0.1
 */
public class Block extends PhysicalAbility {

    private static final String DESCRIPTION = "Reduziere den physischen Schaden des nächsten Angriffs um 7n";

    private static final String NAME = "Block";

    private static final int ZERO = 0;

    private static final int SEVEN = 7;

    /**
     * Instantiates a new Parry.
     *
     * @param abilityLevel the ability level
     */
    public Block(int abilityLevel) {
        super(NAME, AbilityType.DEFENSIVE, DESCRIPTION, false, abilityLevel);
    }

    @Override
    public int calculate(int damage) {
        if (damage >= SEVEN * getAbilityLevel()) {
            return damage - SEVEN * getAbilityLevel();
        }
        return ZERO;
    }

}
