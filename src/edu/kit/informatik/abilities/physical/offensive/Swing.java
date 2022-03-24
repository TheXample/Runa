package edu.kit.informatik.abilities.physical.offensive;

import edu.kit.informatik.abilities.AbilityType;
import edu.kit.informatik.abilities.PhysicalAbility;

/**
 * The type Swing.
 *
 * @author Hannes
 * @version 0.1
 */
public class Swing extends PhysicalAbility {

    private static final String DESCRIPTION = "5n + w physischer Schaden"
            + " Bricht den Focus des Ziels (n ist Fähigkeitslevel und w ist Würfelwurf)";

    private static final String NAME = "Swing";

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
        return 5 * this.getAbilityLevel() + dice;
    }

}
