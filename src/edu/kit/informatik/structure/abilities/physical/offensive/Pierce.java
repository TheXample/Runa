package edu.kit.informatik.structure.abilities.physical.offensive;

import edu.kit.informatik.structure.abilities.AbilityType;
import edu.kit.informatik.structure.abilities.PhysicalAbility;

/**
 * The type Pierce.
 *
 * @author Hannes
 * @version 0.1
 */
public class Pierce extends PhysicalAbility {

    private static final String DESCRIPTION = "7n +  w physischer Schaden"
            + "+ 5n physischer Schaden, falls w ≥ 6 (n ist Fähigkeitslevel und w ist Würfelwurf)";

    private static final String NAME = "Pierce";

    /**
     * Instantiates a new Pierce.
     *
     * @param abilityLevel the ability level
     */
    public Pierce(int abilityLevel) {
        super(NAME, AbilityType.OFFENSIVE, DESCRIPTION, false, abilityLevel);
    }

    @Override
    public int calculate(int dice) {
        if (dice >= 6) {
            return 7 * this.getAbilityLevel() + dice + 5 * this.getAbilityLevel();
        }
        return 7 * this.getAbilityLevel() + dice;
    }

}
