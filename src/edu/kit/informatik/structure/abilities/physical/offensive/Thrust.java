package edu.kit.informatik.structure.abilities.physical.offensive;

import edu.kit.informatik.structure.abilities.AbilityType;
import edu.kit.informatik.structure.abilities.PhysicalAbility;

/**
 * The type Thrust.
 *
 * @author Hannes
 * @version 0.1
 */
public class Thrust extends PhysicalAbility {

    private static final String DESCRIPTION = "6n + physischer Schaden"
            + "+ 4n physischer Schaden, falls w ≥ 6 (n ist Fähigkeitslevel und w ist Würfelwurf)";

    private static final String NAME = "Thrust";

    /**
     * Instantiates a new Thrust.
     *
     * @param abilityLevel the ability level
     */
    public Thrust(int abilityLevel) {
        super(NAME, AbilityType.OFFENSIVE, DESCRIPTION, false, abilityLevel);
    }

    @Override
    public int calculate(int dice) {
        if (dice >= 6) {
            return 6 * this.getAbilityLevel() + dice + 4 * this.getAbilityLevel();
        }
        return 6 * this.getAbilityLevel() + dice;
    }

}
