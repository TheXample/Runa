package edu.kit.informatik.structure.abilities.magical.offensive;

import edu.kit.informatik.structure.abilities.AbilityType;
import edu.kit.informatik.structure.abilities.MagicType;
import edu.kit.informatik.structure.abilities.MagicAbility;

/**
 * The type Fire monster.
 *
 * @author Hannes
 * @version 0.1
 */
public class FireMonster extends MagicAbility {

    private static final String DESCRIPTION = "12𝑛 + 2 magischer Schaden";

    private static final String NAME = "Fire";

    private static final int TWELVE = 12;

    private static final int TWO = 2;

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
        return TWELVE * this.getAbilityLevel() + TWO;
    }
}
