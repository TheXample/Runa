package edu.kit.informatik.abilities.physical.offensive;

import edu.kit.informatik.abilities.AbilityType;
import edu.kit.informatik.abilities.PhysicalAbility;

public class Claw extends PhysicalAbility {

    private static final String description = "6n physischer Schaden, Bricht Runas Focus";

    private static final String name = "Claw";

    public Claw(int abilityLevel) {
        super(name, AbilityType.OFFENSIVE, description, true, abilityLevel);
    }

    public int calculate(int dice) {
        return 6 * this.getAbilityLevel();
    }

}
