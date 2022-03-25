package edu.kit.informatik.structure.abilities.magical.offensive;

import edu.kit.informatik.structure.abilities.AbilityType;
import edu.kit.informatik.structure.abilities.MagicType;
import edu.kit.informatik.structure.abilities.MagicAbility;

/**
 * The type Ice monster.
 *
 * @author Hannes
 * @version 0.1
 */
public class IceMonster extends MagicAbility {

    private static final String DESCRIPTION = "10n + 2 magischer Schaden";

    private static final String NAME = "Ice";

    /**
     * Instantiates a new Ice monster.
     *
     * @param cost         the cost
     * @param abilityLevel the ability level
     */
    public IceMonster(int cost, int abilityLevel) {
        super(NAME, AbilityType.OFFENSIVE, DESCRIPTION, false, cost, MagicType.ICE, abilityLevel);
    }

    @Override
    public int calculate(int focusPoints, MagicType opposingType) {
        return 10 * this.getAbilityLevel() + 2;
    }
}
