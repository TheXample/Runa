package edu.kit.informatik.abilities;

public abstract class PhysicalAbility extends Ability{

    private static final AbilityType usage = AbilityType.PHYSICAL;

    public PhysicalAbility(String name, AbilityType type, String description, boolean breaksFocus, int abilityLevel) {
        super(name, type, description, breaksFocus, abilityLevel, usage);
    }

    public abstract int calculate(int dice);
}
