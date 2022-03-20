package edu.kit.informatik.abilities.physical.defensive;

import edu.kit.informatik.abilities.AbilityType;
import edu.kit.informatik.abilities.PhysicalAbility;

public class Parry extends PhysicalAbility {

    private static final String description = "Reduziere den physischen Schaden des nächsten Angriffs" +
            " um 7";

    private static final String name = "Parry";

    public Parry(int abilityLevel) {
        super(name, AbilityType.DEFENSIVE, description, false, abilityLevel);
    }

    public int calculate(int damage) {
        if (damage >= 7 * getAbilityLevel()) {
            return damage - 7 * getAbilityLevel();
        }
        return 0;
    }

}
