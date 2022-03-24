package edu.kit.informatik.abilities.magical.offensive;

import edu.kit.informatik.abilities.AbilityType;
import edu.kit.informatik.abilities.MagicType;
import edu.kit.informatik.abilities.MagicAbility;

/**
 * The type Fire monster.
 *
 * @author Hannes
 * @version 0.1
 */
public class FireMonster extends MagicAbility {

    private static final String DESCRIPTION = "12𝑛 + 2 magischer Schaden";

    private static final String NAME = "Fire";


    /**
     * Instantiates a new Fire monster.
     *
     * @param cost         the cost
     * @param abilityLevel the ability level
     */
    public FireMonster(int cost, int abilityLevel) {
        super(NAME, AbilityType.OFFENSIVE, DESCRIPTION, false, cost, MagicType.FIRE, abilityLevel);
    }

    @Override
    public int calculate(int focusPoints, MagicType opposingType) {
        return 12 * this.getAbilityLevel() + 2;
    }
}
