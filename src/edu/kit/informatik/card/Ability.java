package edu.kit.informatik.card;

public class Ability extends Card{

    private AbilityType type;

    private final String description;

    private final int damage;

    private final boolean breaksFocus;

    public Ability(String name, AbilityType type, String description, int damage, boolean breaksFocus) {
        super(name);
        this.type = type;
        this.description = description;
        this.damage = damage;
        this.breaksFocus = breaksFocus;
    }

    public AbilityType getType() {
        return type;
    }

    public void setType(AbilityType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isBreaksFocus() {
        return breaksFocus;
    }
}
