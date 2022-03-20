package edu.kit.informatik.abilities;

public abstract class MagicAbility extends Ability{

    private final int cost;

    private final MagicType magicType;

    public MagicAbility(String name, AbilityType type, String description, boolean breaksFocus, int cost, MagicType magicType, int abilityLevel) {
        super(name, type, description, breaksFocus, abilityLevel);
        this.cost = cost;
        this.magicType = magicType;
    }

    public int getCost() {
        return cost;
    }

    public MagicType getMagicType() {
        return magicType;
    }

    public abstract int calculate(int focusPoints, MagicType opposingType);
}
