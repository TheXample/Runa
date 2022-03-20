package edu.kit.informatik.abilities;

public abstract class PhysicalAbility extends Ability{
    public PhysicalAbility(String name, AbilityType type, String description, boolean breaksFocus, int abilityLevel) {
        super(name, type, description, breaksFocus, abilityLevel);
    }

    public abstract int calculate(int dice);
}
