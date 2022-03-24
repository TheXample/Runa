package edu.kit.informatik.abilities.physical.defensive;

import edu.kit.informatik.abilities.AbilityType;
import edu.kit.informatik.abilities.PhysicalAbility;

/**
 * The type Parry.
 *
 * @author Hannes
 * @version 0.1
 */
public class Block extends PhysicalAbility {

    private static final String DESCRIPTION = "Reduziere den physischen Schaden des nächsten Angriffs um 7n";

    private static final String NAME = "Block";

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
        if (damage >= 7 * getAbilityLevel()) {
            return damage - 7 * getAbilityLevel();
        }
        return 0;
    }

}
