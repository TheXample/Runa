package edu.kit.informatik.abilities;

public abstract class physicalAbility  extends Ability{
    public physicalAbility(String name, AbilityType type, String description, boolean breaksFocus, int abilityLevel) {
        super(name, type, description, breaksFocus, abilityLevel);
    }

    public abstract int calculate(int dice);
}
