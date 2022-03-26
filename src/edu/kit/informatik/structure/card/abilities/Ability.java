package edu.kit.informatik.structure.card.abilities;

import edu.kit.informatik.structure.card.Card;

/**
 * The type Ability.
 *
 * @author Hannes
 * @version 0.1
 */
public class Ability extends Card {

    private AbilityType type;

    private final String description;

    private final boolean breaksFocus;

    private int abilityLevel;

    private final AbilityType usageType;

    /**
     * Instantiates a new Ability.
     *
     * @param name         the name
     * @param type         the type
     * @param description  the description
     * @param breaksFocus  the breaks focus
     * @param abilityLevel the ability level
     * @param usageType    the usage type
     */
    public Ability(String name, AbilityType type, String description, boolean breaksFocus,
                   int abilityLevel, AbilityType usageType) {
        super(name);
        this.type = type;
        this.description = description;
        this.breaksFocus = breaksFocus;
        this.abilityLevel = abilityLevel;
        this.usageType = usageType;
    }

    /**
     * Gets ability level.
     *
     * @return the ability level
     */
    public int getAbilityLevel() {
        return abilityLevel;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public AbilityType getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(AbilityType type) {
        this.type = type;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Is breaks focus boolean.
     *
     * @return the boolean
     */
    public boolean isBreaksFocus() {
        return breaksFocus;
    }

    /**
     * Upgrade.
     */
    public void upgrade() {
        this.abilityLevel = abilityLevel + 1;
    }

    /**
     * Equals boolean.
     *
     * @param input the input
     * @return the boolean
     */
    public boolean equalsAbility(Ability input) {
        if (input == null) {
            return false;
        }
        return this.getName().equals(input.getName()) && this.getAbilityLevel() == input.getAbilityLevel();
    }

    /**
     * Gets usage type.
     *
     * @return the usage type
     */
    public AbilityType getUsageType() {
        return usageType;
    }

    /**
     * Calculate int.
     *
     * @param value the value
     * @return the int
     */
    public int calculate(int value) {
        return 0;
    }
}
