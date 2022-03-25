package edu.kit.informatik.structure.abilities.magical.offensive;

import edu.kit.informatik.structure.abilities.AbilityType;
import edu.kit.informatik.structure.abilities.MagicType;
import edu.kit.informatik.structure.abilities.MagicAbility;

/**
 * The type Water monster.
 *
 * @author Hannes
 * @version 0.1
 */
public class WaterMonster extends MagicAbility {

    private static final String DESCRIPTION = "8n + 2 magischer Schaden";

    private static final String NAME = "Water";

    /**
     * Instantiates a new Water monster.
     *
     * @param cost         the cost
     * @param abilityLevel the ability level
     */
    public WaterMonster(int cost, int abilityLevel) {
        super(NAME, AbilityType.OFFENSIVE, DESCRIPTION, false, cost, MagicType.WATER, abilityLevel);
    }

    @Override
    public int calculate(int focusPoints, MagicType opposingType) {
        return 8 * this.getAbilityLevel() + 2;
    }
}
