package edu.kit.informatik.structure.abilities;

/**
 * The type Physical ability.
 *
 * @author Hannes
 * @version 0.1
 */
public abstract class PhysicalAbility extends Ability {

    private static final AbilityType USAGE = AbilityType.PHYSICAL;

    /**
     * Instantiates a new Physical ability.
     *
     * @param name         the name
     * @param type         the type
     * @param description  the description
     * @param breaksFocus  the breaks focus
     * @param abilityLevel the ability level
     */
    public PhysicalAbility(String name, AbilityType type, String description, boolean breaksFocus, int abilityLevel) {
        super(name, type, description, breaksFocus, abilityLevel, USAGE);
    }

    @Override
    public abstract int calculate(int dice);
}
