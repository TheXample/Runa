package edu.kit.informatik.abilities;

import edu.kit.informatik.card.Card;

public class Ability extends Card {

    private AbilityType type;

    private final String description;

    private final boolean breaksFocus;

    private int abilityLevel;

    public Ability(String name, AbilityType type, String description, boolean breaksFocus, int abilityLevel) {
        super(name);
        this.type = type;
        this.description = description;
        this.breaksFocus = breaksFocus;
        this.abilityLevel = abilityLevel;
    }

    public int getAbilityLevel() {
        return abilityLevel;
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

    public boolean isBreaksFocus() {
        return breaksFocus;
    }

    public void upgrade() {
        this.abilityLevel = abilityLevel + 1;
    }

    public boolean equals(Ability input) {
        if (input == null) {
            return false;
        }
        return this.getName().equals(input.getName());
    }
}
