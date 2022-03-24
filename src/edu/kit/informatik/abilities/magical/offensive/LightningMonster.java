package edu.kit.informatik.abilities.magical.offensive;

import edu.kit.informatik.abilities.AbilityType;
import edu.kit.informatik.abilities.MagicType;
import edu.kit.informatik.abilities.MagicAbility;

/**
 * The type Lightning monster.
 * @author Hannes
 * @version 0.1
 */
public class LightningMonster extends MagicAbility {

    private static final String DESCRIPTION = "14n + 2 magischer Schaden";

    private static final String NAME = "Lightning";

    /**
     * Instantiates a new Lightning monster.
     *
     * @param cost         the cost
     * @param abilityLevel the ability level
     */
    public LightningMonster(int cost, int abilityLevel) {
        super(NAME, AbilityType.OFFENSIVE, DESCRIPTION, false, cost, MagicType.LIGHTNING, abilityLevel);
    }

    @Override
    public int calculate(int focusPoints, MagicType opposingType) {
        return 14 * this.getAbilityLevel() + 2;
    }
}
