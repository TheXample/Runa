package edu.kit.informatik.abilities.physical.offensive;

import edu.kit.informatik.abilities.AbilityType;
import edu.kit.informatik.abilities.physicalAbility;

public class Slash extends physicalAbility{

    private static final String description = "4n + w" +
            " bricht den Fokus des Ziels (n ist Fähigkeitslevel und w ist Würfelwurf)";

    private static final String name = "Slash";

    public Slash(int abilityLevel) {
        super(name, AbilityType.OFFENSIVE, description, true, abilityLevel);
    }

    public int calculate(int dice) {
        return 4 * this.getAbilityLevel() + dice;
    }

}
