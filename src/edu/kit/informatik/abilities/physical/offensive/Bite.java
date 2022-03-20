package edu.kit.informatik.abilities.physical.offensive;

import edu.kit.informatik.abilities.AbilityType;
import edu.kit.informatik.abilities.PhysicalAbility;

public class Bite extends PhysicalAbility {

    private static final String description = "10n physischer Schaden";

    private static final String name = "Bite";

    public Bite(int abilityLevel) {
        super(name, AbilityType.OFFENSIVE, description, false, abilityLevel);
    }

    public int calculate(int dice) {
        return 10 * this.getAbilityLevel();
    }

}
