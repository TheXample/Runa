package edu.kit.informatik.abilities.physical.offensive;

import edu.kit.informatik.abilities.AbilityType;
import edu.kit.informatik.abilities.physicalAbility;

public class Thrust extends physicalAbility{

    private static final String description = "6n + physischer Schaden" +
            "+ 4n physischer Schaden, falls w ≥ 6 (n ist Fähigkeitslevel und w ist Würfelwurf)";

    private static final String name = "Thrust";

    public Thrust(int abilityLevel) {
        super(name, AbilityType.OFFENSIVE, description, false, abilityLevel);
    }

    public int calculate(int dice) {
        if (dice >= 6) {
            return 6 * this.getAbilityLevel() + 4 * this.getAbilityLevel();
        }
        return 6 * this.getAbilityLevel();
    }

}
