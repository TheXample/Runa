package edu.kit.informatik.abilities.physical.offensive;

import edu.kit.informatik.abilities.AbilityType;
import edu.kit.informatik.abilities.PhysicalAbility;

public class Scratch extends PhysicalAbility {

    private static final String description = "5n physischer Schaden, Bricht Runas Focus";

    private static final String name = "Scratch";

    public Scratch(int abilityLevel) {
        super(name, AbilityType.OFFENSIVE, description, true, abilityLevel);
    }

    public int calculate(int dice) {
        return 5 * this.getAbilityLevel();
    }

}
