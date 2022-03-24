package edu.kit.informatik.abilities.physical.offensive;

import edu.kit.informatik.abilities.AbilityType;
import edu.kit.informatik.abilities.PhysicalAbility;

/**
 * The type Slash.
 * @author Hannes
 * @version 0.1
 */
public class Slash extends PhysicalAbility {

    private static final String DESCRIPTION = "4n + w"
            + " bricht den Fokus des Ziels (n ist Fähigkeitslevel und w ist Würfelwurf)";

    private static final String NAME = "Slash";

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
        return 4 * this.getAbilityLevel() + dice;
    }

}
