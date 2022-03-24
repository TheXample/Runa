package edu.kit.informatik.abilities.physical.offensive;

import edu.kit.informatik.abilities.AbilityType;
import edu.kit.informatik.abilities.PhysicalAbility;

public class Smash extends PhysicalAbility {

    private static final String description = "8n physischer Schaden";

    private static final String name = "Smash";

    public Smash(int abilityLevel) {
        super(name, AbilityType.OFFENSIVE, description, false, abilityLevel);
    }

    public int calculate(int dice) {
        return 8 * this.getAbilityLevel();
    }

}
