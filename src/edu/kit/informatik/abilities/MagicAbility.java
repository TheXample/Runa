package edu.kit.informatik.abilities;

/**
 * The type Magic ability.
 *
 * @author Hannes
 * @version 0.1
 */
public abstract class MagicAbility extends Ability {

    private static final AbilityType USAGE = AbilityType.MAGIC;

    private final int cost;

    private final MagicType magicType;

    /**
     * Instantiates a new Magic ability.
     *
     * @param name         the name
     * @param type         the type
     * @param description  the description
     * @param breaksFocus  the breaks focus
     * @param cost         the cost
     * @param magicType    the magic type
     * @param abilityLevel the ability level
     */
    public MagicAbility(String name, AbilityType type, String description, boolean breaksFocus,
                        int cost, MagicType magicType, int abilityLevel) {
        super(name, type, description, breaksFocus, abilityLevel, USAGE);
        this.cost = cost;
        this.magicType = magicType;
    }

    /**
     * Gets cost.
     *
     * @return the cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * Gets magic type.
     *
     * @return the magic type
     */
    public MagicType getMagicType() {
        return magicType;
    }

    /**
     * Calculate int.
     *
     * @param focusPoints  the focus points
     * @param opposingType the opposing type
     * @return the int
     */
    public abstract int calculate(int focusPoints, MagicType opposingType);
}
