package edu.kit.informatik.abilities.physical.offensive;

import edu.kit.informatik.abilities.AbilityType;
import edu.kit.informatik.abilities.PhysicalAbility;

public class Pierce extends PhysicalAbility {

    private static final String description = "7n +  w physischer Schaden" +
            "+ 5n physischer Schaden, falls w ≥ 6 (n ist Fähigkeitslevel und w ist Würfelwurf)";

    private static final String name = "Pierce";

    public Pierce(int abilityLevel) {
        super(name, AbilityType.OFFENSIVE, description, false, abilityLevel);
    }

    public int calculate(int dice) {
        if (dice >= 6) {
            return 7 * this.getAbilityLevel() + dice + 5 * this.getAbilityLevel();
        }
        return 7 * this.getAbilityLevel() + dice;
    }

}
