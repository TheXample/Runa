package edu.kit.informatik.card;

public class magicAbility extends Ability{

    private final int cost;

    private final MagicType magicType;

    public magicAbility(String name, AbilityType type, String description, int damage, boolean breaksFocus, int cost, MagicType magicType) {
        super(name, type, description, damage, breaksFocus);
        this.cost = cost;
        this.magicType = magicType;
    }

    public int getCost() {
        return cost;
    }

    public MagicType getMagicType() {
        return magicType;
    }
}
