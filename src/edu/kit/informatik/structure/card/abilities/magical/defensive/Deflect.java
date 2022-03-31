package edu.kit.informatik.structure.card.abilities.magical.defensive;

import edu.kit.informatik.structure.card.abilities.AbilityType;
import edu.kit.informatik.structure.card.abilities.MagicType;
import edu.kit.informatik.structure.card.abilities.MagicAbility;

/**
 * The type Deflect.
 *
 * @author Hannes
 * @version 0.1
 */
public class Deflect extends MagicAbility {

    private static final String DESCRIPTION = "Reduces next attacks damage by 11n + 2 magic damage";

    private static final String NAME = "Deflect";

    private static final int COST = 0;

    private static final int TWO = 2;

    private static final int ELEVEN = 11;

    private static final int TEN = 10;

    /**
     * Instantiates a new Deflect.
     *
     * @param abilityLevel the ability level
     */
    public Deflect(int abilityLevel) {
        super(NAME, AbilityType.DEFENSIVE, DESCRIPTION, false, COST, MagicType.NONE, abilityLevel);
    }

    @Override
    public int calculate(int damage, MagicType opposingType) {
        if (damage > ELEVEN * this.getAbilityLevel() + TWO) {
            return damage - TEN * this.getAbilityLevel();
        }
        return COST;
    }
}
