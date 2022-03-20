package edu.kit.informatik.abilities.physical.offensive;

import edu.kit.informatik.abilities.AbilityType;
import edu.kit.informatik.abilities.PhysicalAbility;

public class Swing extends PhysicalAbility {

    private static final String description = "5n + w physischer Schaden" +
            " Bricht den Focus des Ziels (n ist Fähigkeitslevel und w ist Würfelwurf)";

    private static final String name = "Swing";

    public Swing(int abilityLevel) {
        super(name, AbilityType.OFFENSIVE, description, true, abilityLevel);
    }

    public int calculate(int dice) {
        return 5 * this.getAbilityLevel() + dice;
    }

}
