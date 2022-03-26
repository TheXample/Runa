package edu.kit.informatik.structure.abilities.magical.defensive;

import edu.kit.informatik.structure.abilities.AbilityType;
import edu.kit.informatik.structure.abilities.MagicType;
import edu.kit.informatik.structure.abilities.MagicAbility;

/**
 * The type Reflect.
 *
 * @author Hannes
 * @version 0.1
 */
public class Reflect extends MagicAbility {

    private static final String DESCRIPTION = "Reflektiere bis zu 10n magischen Schaden. Der restliche "
            + "Schaden trifft Runa.";

    private static final String NAME = "Reflect";

    private static final int COST = 0;

    private static final int TEN = 10;

    /**
     * Instantiates a new Reflect.
     *
     * @param abilityLevel the ability level
     */
    public Reflect(int abilityLevel) {
        super(NAME, AbilityType.DEFENSIVE, DESCRIPTION, false, COST, MagicType.NONE, abilityLevel);
    }

    @Override
    public int calculate(int damage, MagicType opposingType) {
        if (damage > TEN * this.getAbilityLevel()) {
            return TEN * this.getAbilityLevel();
        }
        return damage;
    }
}
